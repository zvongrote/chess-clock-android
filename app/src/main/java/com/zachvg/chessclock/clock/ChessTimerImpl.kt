package com.zachvg.chessclock.clock

import android.os.CountDownTimer
import com.zachvg.chessclock.domain.ChessTimer
import com.zachvg.chessclock.domain.TimerHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val COUNTDOWN_INTERVAL = 1_000L

class ChessTimerImpl(private val timerCoroutineScope: CoroutineScope) : ChessTimer {

    override var eventHandler: TimerHandler? = null

    override var totalTimeMillis = 10_000L

    private var timeLeft = totalTimeMillis

    private var countdownTimer: CountDownTimer? = null

    private var state = State.NOT_STARTED

    private val _time = MutableStateFlow<Long>(totalTimeMillis)
    override val time: StateFlow<Long> = _time

    override fun start() {
        when (state) {
            State.RUNNING -> { /* Do nothing */ }
            else -> { startTimer() }
        }
    }

    private fun startTimer() {
        countdownTimer = newCountdownTimer(timeLeft, COUNTDOWN_INTERVAL).apply {
            start()
        }

        state = State.RUNNING
    }

    override fun pause() {
        when (state) {
            State.PAUSED -> { /* Do nothing */ }
            else -> { pauseTimer() }
        }
    }

    private fun pauseTimer() {
        countdownTimer?.cancel()
        countdownTimer = null

        state = State.PAUSED
    }

    override fun cancel() {
        when (state) {
            State.RUNNING, State.PAUSED -> { cancelTimer() }
            else -> { /* Do nothing */ }
        }
    }

    private fun cancelTimer() {
        countdownTimer?.cancel()
        countdownTimer = null

        state = State.CANCELED
    }

    override fun reset() {
        when (state) {
            State.NOT_STARTED -> { /* Do nothing */ }
            else -> { resetTimer() }
        }
    }

    private fun resetTimer() {
        countdownTimer?.cancel()
        countdownTimer = null

        _time.value = totalTimeMillis
        timeLeft = totalTimeMillis

        state = State.NOT_STARTED
    }

    private fun newCountdownTimer(millisInFuture: Long, countdownInterval: Long) =
        object : CountDownTimer(millisInFuture, countdownInterval) {

            override fun onTick(millisUntilFinished: Long) {
                timerCoroutineScope.launch {
                    _time.emit(millisUntilFinished)
                }

                timeLeft = millisUntilFinished
            }

            override fun onFinish() {
                timerCoroutineScope.launch {
                    _time.emit(0)
                }

                countdownTimer = null

                state = State.FINISHED
            }

        }

    private enum class State {
        NOT_STARTED,
        RUNNING,
        PAUSED,
        CANCELED,
        FINISHED
    }
}