package com.zachvg.chessclock.clock

import com.zachvg.chessclock.domain.ChessClock
import com.zachvg.chessclock.domain.ChessTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChessClockImpl(
    private val player1Timer: ChessTimer,
    private val player2Timer: ChessTimer
) : ChessClock {

    override val player1Time: StateFlow<Long> = player1Timer.time

    override val player2Time: StateFlow<Long> = player2Timer.time

    private val _activePlayer = MutableStateFlow(ChessClock.Player.NONE)
    override val activePlayer = _activePlayer.asStateFlow()

    private val _gameState = MutableStateFlow(ChessClock.GameState.NOT_STARTED)
    override val gameState = _gameState.asStateFlow()

    override fun pause() {
        when (gameState.value) {
            ChessClock.GameState.IN_PROGRESS -> pauseGame()
            else -> Unit
        }
    }

    private fun pauseGame() {

        pauseActiveTimer()

        _gameState.value = ChessClock.GameState.PAUSED

        _activePlayer.value = ChessClock.Player.NONE
    }

    private fun pauseActiveTimer() = when (activePlayer.value) {
        ChessClock.Player.PLAYER_1 -> player1Timer.pause()
        ChessClock.Player.PLAYER_2 -> player2Timer.pause()
        else -> Unit
    }

    override fun reset() {
        player1Timer.reset()
        player2Timer.reset()

        _gameState.value = ChessClock.GameState.NOT_STARTED

        _activePlayer.value = ChessClock.Player.NONE
    }

    override fun onPlayerPress(player: ChessClock.Player) {
        when (gameState.value) {
            ChessClock.GameState.NOT_STARTED -> startOppositePlayerFrom(player)
            ChessClock.GameState.IN_PROGRESS -> toggleActivePlayer()
            ChessClock.GameState.PAUSED -> startOppositePlayerFrom(player)
            ChessClock.GameState.FINISHED -> Unit
        }
    }

    private fun startOppositePlayerFrom(player: ChessClock.Player) {
        when (player) {
            ChessClock.Player.PLAYER_1 -> {
                player2Timer.start()
                _activePlayer.value = ChessClock.Player.PLAYER_2
            }
            ChessClock.Player.PLAYER_2 -> {
                player1Timer.start()
                _activePlayer.value = ChessClock.Player.PLAYER_1
            }
            else -> Unit
        }
    }

    private fun toggleActivePlayer() {
        when (activePlayer.value) {
            ChessClock.Player.PLAYER_1 -> {
                player1Timer.pause()
                player2Timer.start()

                _activePlayer.value = ChessClock.Player.PLAYER_2
            }
            ChessClock.Player.PLAYER_2 -> {
                player2Timer.pause()
                player1Timer.start()

                _activePlayer.value = ChessClock.Player.PLAYER_1
            }
            else -> Unit
        }
    }
}