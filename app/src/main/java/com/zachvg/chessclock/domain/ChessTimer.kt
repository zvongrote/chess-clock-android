package com.zachvg.chessclock.domain

import kotlinx.coroutines.flow.StateFlow

interface ChessTimer {

    var totalTimeMillis: Long

    val time: StateFlow<Long>

    val finished: StateFlow<Boolean>

    fun start()

    fun pause()

    fun cancel()

    fun reset()
}