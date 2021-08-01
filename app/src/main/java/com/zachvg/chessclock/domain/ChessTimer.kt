package com.zachvg.chessclock.domain

import kotlinx.coroutines.flow.StateFlow

interface ChessTimer {

    var eventHandler: TimerHandler?

    var totalTimeMillis: Long

    val time: StateFlow<Long>

    fun start()

    fun pause()

    fun cancel()

    fun reset()
}