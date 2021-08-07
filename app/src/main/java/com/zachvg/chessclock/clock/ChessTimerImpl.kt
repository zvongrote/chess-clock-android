package com.zachvg.chessclock.clock

import android.os.CountDownTimer
import com.zachvg.chessclock.domain.ChessTimer
import com.zachvg.chessclock.domain.TimerHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val COUNTDOWN_INTERVAL = 1_000L

class ChessTimerImpl(private val timerCoroutineScope: CoroutineScope) : ChessTimer {

    override var totalTimeMillis = 10_000L

    private var timeLeft = totalTimeMillis

    private var countdownTimer: CountDownTimer? = null

    private var state = State.NOT_STARTED

    private val _time = MutableStateFlow(totalTimeMillis)
    override val time = _time.asStateFlow()

    private val _finished = MutableStateFlow(false)
    override val finished = _finished.asStateFlow()

    override fun start() {
        when (state) {
            State.NOT_STARTED, State.PAUSED -> startTimer()
            else -> Unit
        }
    }

    private fun startTimer() {
        countdownTimer = newCountdownTimer(timeLeft, COUNTDOWN_INTERVAL).also {
            start()
        }

        state = State.RUNNING
    }

    override fun pause() {
        when (state) {
            State.RUNNING -> pauseTimer()
            else -> Unit
        }
    }

    private fun pauseTimer() {
        countdownTimer?.cancel()
        countdownTimer = null

        state = State.PAUSED
    }

    override fun cancel() {
        when (state) {
            State.RUNNING, State.PAUSED -> cancelTimer()
            else -> Unit
        }
    }

    private fun cancelTimer() {
        countdownTimer?.cancel()
        countdownTimer = null

        state = State.CANCELED
    }

    override fun reset() {
        when (state) {
            State.NOT_STARTED -> Unit
            else -> resetTimer()
        }
    }

    private fun resetTimer() {
        countdownTimer?.cancel()
        countdownTimer = null

        _time.value = totalTimeMillis
        timeLeft = totalTimeMillis

        state = State.NOT_STARTED

        _finished.value = false
    }

    private fun newCountdownTimer(millisInFuture: Long, countdownInterval: Long) =
        object : CountDownTimer(millisInFuture, countdownInterval) {

            override fun onTick(millisUntilFinished: Long) {
                timerCoroutineScope.launch {
                    _time.value = millisUntilFinished
                }

                timeLeft = millisUntilFinished
            }

            override fun onFinish() {
                timerCoroutineScope.launch {
                    _time.value = 0
                }

                countdownTimer = null

                state = State.FINISHED

                _finished.value = true
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