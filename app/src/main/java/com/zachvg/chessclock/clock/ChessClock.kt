package com.zachvg.chessclock.clock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/*
This class is responsible for handling the state of the
chess clock.
 */
class ChessClock(
    private var player1TotalTime: Long = 0L,
    private var player2TotalTime: Long = 0L
) {

    // States
    var state: GameState = GameState.NOT_STARTED
    var activePlayer: Player = Player.NONE

    // Time
    private val _player1TimeRemaining = MutableLiveData<Long>(player1TotalTime)
    val player1TimeRemaining: LiveData<Long>
        get() = _player1TimeRemaining

    private val _player2TimeRemaining = MutableLiveData<Long>(player2TotalTime)
    val player2TimeRemaining: LiveData<Long>
        get() = _player2TimeRemaining

    // Player timers
    private val player1Timer = object : PlayerCountDownTimer(player1TotalTime) {
        override fun onFinish() {
            _player1TimeRemaining.value = 0L
            finishGame()
        }

        override fun onTick(millisUntilFinished: Long) {
            _player1TimeRemaining.value = millisUntilFinished
        }

    }

    private val player2Timer = object : PlayerCountDownTimer(player2TotalTime) {
        override fun onFinish() {
            _player2TimeRemaining.value = 0L
            finishGame()
        }

        override fun onTick(millisUntilFinished: Long) {
            _player2TimeRemaining.value = millisUntilFinished
        }

    }

    fun newGame(player1Time: Long, player2Time: Long) {
        player1TotalTime = player1Time
        player2TotalTime = player2Time

        reset()
    }

    fun reset() {
        state = GameState.NOT_STARTED
        activePlayer = Player.NONE

        _player1TimeRemaining.value = player1TotalTime
        _player2TimeRemaining.value = player2TotalTime
    }

    private fun finishGame() {
        state = GameState.FINISHED
        activePlayer
    }

    enum class Player {
        NONE,
        PLAYER_1,
        PLAYER_2
    }

    enum class GameState {
        NOT_STARTED,
        IN_PROGRESS,
        PAUSED,
        FINISHED
    }
}