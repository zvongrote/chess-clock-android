package com.zachvg.chessclock.domain

import kotlinx.coroutines.flow.StateFlow

interface ChessClock {

    val player1Time: StateFlow<Long>

    val player2Time: StateFlow<Long>

    val player1State: StateFlow<PlayerState>

    val player2State: StateFlow<PlayerState>

    val activePlayer: StateFlow<Player>

    val gameState: StateFlow<GameState>

    fun pause()

    fun reset()

    fun onPlayerPress(player: Player)

    enum class Player {
        NONE,
        PLAYER_1,
        PLAYER_2
    }

    enum class PlayerState {
        INACTIVE,
        ACTIVE,
        OUT_OF_TIME
    }

    enum class GameState {
        NOT_STARTED,
        IN_PROGRESS,
        PAUSED,
        FINISHED
    }
}