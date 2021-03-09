package com.zachvg.chessclock

import androidx.lifecycle.ViewModel

private enum class GameState {
    NOT_STARTED,
    IN_PROGRESS,
    PAUSED,
    FINISHED
}

private enum class Player {
    PLAYER_1,
    PLAYER_2
}

class ClockViewModel : ViewModel() {

    private var currentPlayer = Player.PLAYER_1

    fun onPlayer1ButtonClick() {

    }

    fun onPlayer2ButtonClick() {

    }
}