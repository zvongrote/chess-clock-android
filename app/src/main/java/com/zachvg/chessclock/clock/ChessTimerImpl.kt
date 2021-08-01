package com.zachvg.chessclock.clock

import android.os.CountDownTimer
import com.zachvg.chessclock.domain.ChessTimer
import com.zachvg.chessclock.domain.TimerHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

private const val COUNTDOWN_INTERVAL = 1_000L

class ChessTimerImpl(coroutineScope: CoroutineScope) : ChessTimer {

    override var eventHandler: TimerHandler? = null

    override var totalTimeMillis = 10_000L

    private var timeLeft = totalTimeMillis

    private var countdownTimer: CountDownTimer? = null

    private var state = State.NOT_STARTED

    private val _time = MutableStateFlow<Long>(totalTimeMillis)
    override val time: StateFlow<Long> = _time

    override fun start() {
        when (state) {
            State.NOT_STARTED, State.
        }
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }

    private fun newCountdownTimer(millisInFuture: Long, countdownInterval: Long) =
        object : CountDownTimer(millisInFuture, countdownInterval) {

            override fun onTick(millisUntilFinished: Long) {
                TODO("Not yet implemented")
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }

    private enum class State {
        NOT_STARTED,
        RUNNING,
        PAUSED,
        FINISHED
    }
}