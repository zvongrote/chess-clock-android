package com.zachvg.chessclock.clock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ClockViewModel : ViewModel() {

    private val clock = ChessClock(10_000L, 11_000L)

    val player1Time: LiveData<String> =
        Transformations.map(clock.player1TimeRemaining) { millis -> millisToTimeString(millis) }

    val player2Time: LiveData<String> =
        Transformations.map(clock.player2TimeRemaining) { millis -> millisToTimeString(millis) }

    fun onPlayer1ButtonClick() {
        // TODO
    }

    fun onPlayer2ButtonClick() {
        // TODO
    }

    fun onSettingsButtonClick() {
        // TODO
    }

    fun onPauseButtonClick() {
        // TODO
    }

    fun onResetButtonClick() {
        // TODO
    }
}