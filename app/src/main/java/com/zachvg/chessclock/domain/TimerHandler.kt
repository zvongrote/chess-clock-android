package com.zachvg.chessclock.domain

interface TimerHandler {

    fun onTick(millisUntilFinished: Long)

    fun onFinish()
}