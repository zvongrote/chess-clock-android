package com.zachvg.chessclock.ui.clock

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zachvg.chessclock.SingleLiveEvent
import com.zachvg.chessclock.clock.ChessClock
import com.zachvg.chessclock.millisToTimeString

class ClockViewModel : ViewModel() {

    private val clock = ChessClock(10_000L, 11_000L)

    val player1Time: LiveData<String> =
        Transformations.map(clock.player1TimeRemaining) { millis -> millisToTimeString(millis) }

    val player2Time: LiveData<String> =
        Transformations.map(clock.player2TimeRemaining) { millis -> millisToTimeString(millis) }

    val showPauseButton: LiveData<Int> =
        Transformations.map(clock.gameState) { gameState -> if (gameState == ChessClock.GameState.IN_PROGRESS) View.VISIBLE else View.INVISIBLE }

    val player1State = clock.player1State

    val player2State = clock.player2State

    val showResetDialog = SingleLiveEvent<Boolean>()

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
        clock.pause()
        showResetDialog.value = true
    }

    fun resetClock() {
        clock.reset()
    }
}