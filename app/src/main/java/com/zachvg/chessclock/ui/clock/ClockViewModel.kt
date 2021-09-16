package com.zachvg.chessclock.ui.clock

import android.view.View
import androidx.lifecycle.*
import com.zachvg.chessclock.domain.ChessClock
import com.zachvg.chessclock.millisToTimeString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClockViewModel @Inject constructor(private val clock: ChessClock) : ViewModel() {

    val player1Time: LiveData<String> =
        Transformations.map(clock.player1Time.asLiveData()) { millis -> millisToTimeString(millis) }

    val player2Time: LiveData<String> =
        Transformations.map(clock.player2Time.asLiveData()) { millis -> millisToTimeString(millis) }

    val showPauseButton: LiveData<Int> =
        Transformations.map(clock.gameState.asLiveData()) { gameState -> if (gameState == ChessClock.GameState.IN_PROGRESS) View.VISIBLE else View.INVISIBLE }

    val player1State = clock.player1State.asLiveData()

    val player2State = clock.player2State.asLiveData()

    sealed class Event {
        object ShowResetDialog : Event()
    }

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()


    fun onPlayer1ButtonClick() {
        clock.onPlayerPress(ChessClock.Player.PLAYER_1)
    }

    fun onPlayer2ButtonClick() {
        clock.onPlayerPress(ChessClock.Player.PLAYER_2)
    }

    fun onSettingsButtonClick() {
        // TODO
    }

    fun onPauseButtonClick() {
        clock.pause()
    }

    fun onResetButtonClick() {
        clock.pause()
        viewModelScope.launch { eventChannel.send(Event.ShowResetDialog) }
    }

    fun resetClock() {
        clock.reset()
    }
}