package com.zachvg.chessclock.clock

import androidx.lifecycle.LiveData

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

    // Player timers
    private val player1Timer = object : PlayerCountDownTimer(player1TotalTime) {
        override fun onFinish() {
            finishGame()
        }

        override fun onTick(millisUntilFinished: Long) {}

    }

    private val player2Timer = object : PlayerCountDownTimer(player2TotalTime) {
        override fun onFinish() {
            finishGame()
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
        activePlayer = Player.NONE
    }

    fun onPlayerButtonClick(playerClicked: Player) {
        when (state) {
            GameState.NOT_STARTED -> startOppositeTimerFrom(playerClicked)
            GameState.IN_PROGRESS -> toggle(playerClicked)
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
                activePlayer = Player.PLAYER_2
            }
            Player.PLAYER_2 -> {
                player2Timer.pause()
                player1Timer.start()
                activePlayer = Player.PLAYER_1
            }
            Player.NONE -> Unit // Do nothing
        }
    }

    private fun startOppositeTimerFrom(player: Player) {
        when (player) {
            Player.PLAYER_1 -> player2Timer.start()
            Player.PLAYER_2 -> player1Timer.start()
            Player.NONE -> Unit // Do nothing
        }

        state = GameState.IN_PROGRESS
    }

    private fun finishGame() {
        state = GameState.FINISHED
        activePlayer = Player.NONE
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