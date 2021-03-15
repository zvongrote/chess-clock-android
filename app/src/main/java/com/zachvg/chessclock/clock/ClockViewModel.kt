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

    val player1State = clock.player1State

    val player2State = clock.player2State

    fun onPlayer1ButtonClick() {
        clock.onPlayerButtonClick(ChessClock.Player.PLAYER_1)
    }

    fun onPlayer2ButtonClick() {
        clock.onPlayerButtonClick(ChessClock.Player.PLAYER_2)
    }

    fun onSettingsButtonClick() {
        // TODO
    }

    fun onPauseButtonClick() {
        clock.pause()
    }

    fun onResetButtonClick() {
        clock.reset()
    }
}