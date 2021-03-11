package com.zachvg.chessclock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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