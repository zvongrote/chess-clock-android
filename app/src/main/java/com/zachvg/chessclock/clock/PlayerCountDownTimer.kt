package com.zachvg.chessclock.clock

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// Adds a rudimentary pause feature to the built in Android CountDownTimer

// This implementation isn't very accurate since the timeLeftMillis variable
// is only updated during the onTick method, which depends on the countdown
// interval. The problem occurs when the timer is paused in between calls to
// onTick; the time elapsed after a call to onTick and the pause is lost,
// which results in slight increases the the actual time.
//
// For example: if the time starts at 10 seconds and the countdown interval
// is 1 second, and it's paused at 9.1 seconds, then the onTick event hasn't
// fired and the timeLeftMillis will still be set to 10 seconds. So, when
// it's unpaused the timer will start at 10 seconds again, causing a total
// increase of 0.9 seconds in the total time.
//
// Using a shorter countdown interval helps the reduce the problem, but it's
// possible that slower hardware may not be able to handle very short intervals.
// The default is set to 100ms right now.
//
// This could be an especially bad problem for the a chess match that has a
// an increment since a faster player could end up with much more time than
// a slower one. This needs to be fixed in the future, but it will work for now.
// One possible solution:
// https://stackoverflow.com/a/9802426/9633447
abstract class PlayerCountDownTimer(millisInFuture: Long, private var countDownInterval: Long = 100L) {
    private var totalTimeMillis = millisInFuture

    private val _timeLeftMillis = MutableLiveData(millisInFuture)
    val timeLeftMillis: LiveData<Long>
        get() = _timeLeftMillis

    private var timer = newCountDownTimer(millisInFuture, countDownInterval)

    /**
     * Starts the timer.
     */
    fun start() {
        timer.start()
    }

    /**
     *  Resets the timer back to [totalTimeMillis].
     */
    fun reset() {
        timer = newCountDownTimer(totalTimeMillis, countDownInterval)
        _timeLeftMillis.value = totalTimeMillis
    }

    /**
     * Pauses the timer with [timeLeftMillis] remaining.
     */
    fun pause() {
        timer.cancel()
        timer = newCountDownTimer(timeLeftMillis.value!!, countDownInterval)
    }

    /**
     * Cancels the timer.
     */
    // Not sure if there's a use case for only cancelling the timer without also performing
    // another action.
    fun cancel() {
        timer.cancel()
    }

    /**
     * Sets the timer to a new time
     *
     * @param newMillisInFuture New time to set in milliseconds
     */
    fun setTime(newMillisInFuture: Long) {
        timer.cancel()
        timer = newCountDownTimer(newMillisInFuture, countDownInterval)
        _timeLeftMillis.value = newMillisInFuture
    }

    private fun newCountDownTimer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {
        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeftMillis.value = millisUntilFinished
                this@PlayerCountDownTimer.onTick(millisUntilFinished)
            }

            override fun onFinish() {
                _timeLeftMillis.value = 0L
                this@PlayerCountDownTimer.onFinish()
            }

        }
    }

    abstract fun onFinish()

    abstract fun onTick(millisUntilFinished: Long)
}