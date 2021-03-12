package com.zachvg.chessclock.clock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ClockViewModel : ViewModel() {

    private val _player1Time = MutableLiveData<Long>(0)
    val player1Time: LiveData<String> = Transformations.map(_player1Time) { millis -> millisToTimeString(millis) }

    private val _player2Time = MutableLiveData<Long>(0)
    val player2Time: LiveData<String> = Transformations.map(_player2Time) { millis -> millisToTimeString(millis) }

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