package com.zachvg.chessclock.clock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ClockViewModel : ViewModel() {

    private val _player1Time = MutableLiveData<Long>()
    val player1Time = Transformations.map(_player1Time) { millis -> millisToTimeString(millis) }

    private val _player2Time = MutableLiveData<Long>()
    val player2Time = Transformations.map(_player2Time) { millis -> millisToTimeString(millis) }

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