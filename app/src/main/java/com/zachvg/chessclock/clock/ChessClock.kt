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
        private set

    private val _player1State = MutableLiveData(PlayerState.INACTIVE)
    val player1State: LiveData<PlayerState>
        get() = _player1State

    private val _player2State = MutableLiveData(PlayerState.INACTIVE)
    val player2State: LiveData<PlayerState>
        get() = _player2State

    // Player timers
    private val player1Timer = object : PlayerCountDownTimer(player1TotalTime) {
        override fun onFinish() {
            onOutOfTime(Player.PLAYER_1)
        }

        override fun onTick(millisUntilFinished: Long) {}

    }

    private val player2Timer = object : PlayerCountDownTimer(player2TotalTime) {
        override fun onFinish() {
            onOutOfTime(Player.PLAYER_2)
        }

        override fun onTick(millisUntilFinished: Long) {}

    }

    // Time
    val player1TimeRemaining: LiveData<Long> = player1Timer.timeLeftMillis
    val player2TimeRemaining: LiveData<Long> = player2Timer.timeLeftMillis

    fun newGame(player1Time: Long, player2Time: Long) {
        player1TotalTime = player1Time
        player2TotalTime = player2Time

        reset()
    }

    fun reset() {
        player1Timer.reset()
        player2Timer.reset()

        state = GameState.NOT_STARTED
        _player1State.value = PlayerState.INACTIVE
        _player2State.value = PlayerState.INACTIVE
    }

    fun pause() {
        if (state == GameState.IN_PROGRESS) {
            if (player1State.value == PlayerState.ACTIVE) player1Timer.pause()
            if (player2State.value == PlayerState.ACTIVE) player2Timer.pause()

            state = GameState.PAUSED
            _player1State.value = PlayerState.INACTIVE
            _player2State.value = PlayerState.INACTIVE
        }
    }

    fun onPlayerButtonClick(playerClicked: Player) {
        when (state) {
            GameState.NOT_STARTED -> startOppositeTimerFrom(playerClicked)
            GameState.IN_PROGRESS -> {
                if ((player1State.value == PlayerState.ACTIVE) and (playerClicked == Player.PLAYER_1)) {
                    toggle(Player.PLAYER_1)
                }
                if ((player2State.value == PlayerState.ACTIVE) and (playerClicked == Player.PLAYER_2)) {
                    toggle(Player.PLAYER_2)
                }
            }
            GameState.PAUSED -> startOppositeTimerFrom(playerClicked)
            GameState.FINISHED -> {
            }
        }
    }

    private fun toggle(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                player1Timer.pause()
                player2Timer.start()

                _player1State.value = PlayerState.INACTIVE
                _player2State.value = PlayerState.ACTIVE
            }
            Player.PLAYER_2 -> {
                player1Timer.start()
                player2Timer.pause()

                _player1State.value = PlayerState.ACTIVE
                _player2State.value = PlayerState.INACTIVE
            }
            Player.NONE -> Unit // Do nothing
        }
    }

    private fun startOppositeTimerFrom(player: Player) {
        when (player) {
            Player.PLAYER_1 -> {
                player2Timer.start()
                _player2State.value = PlayerState.ACTIVE
            }
            Player.PLAYER_2 -> {
                player1Timer.start()
                _player1State.value = PlayerState.ACTIVE
            }
            Player.NONE -> Unit // Do nothing
        }

        state = GameState.IN_PROGRESS
    }

    private fun onOutOfTime(player: Player) {
        state = GameState.FINISHED

        if (player == Player.PLAYER_1) _player1State.value = PlayerState.OUT_OF_TIME
        if (player == Player.PLAYER_2) _player2State.value = PlayerState.OUT_OF_TIME
    }

    enum class PlayerState {
        INACTIVE,
        ACTIVE,
        OUT_OF_TIME
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