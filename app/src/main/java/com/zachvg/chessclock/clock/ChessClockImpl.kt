package com.zachvg.chessclock.clock

import com.zachvg.chessclock.domain.ChessClock
import com.zachvg.chessclock.domain.ChessTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChessClockImpl(
    private val player1Timer: ChessTimer,
    private val player2Timer: ChessTimer,
    private val coroutineScope: CoroutineScope
) : ChessClock {

    override val player1Time: StateFlow<Long> = player1Timer.time

    override val player2Time: StateFlow<Long> = player2Timer.time

    private val _player1State = MutableStateFlow(ChessClock.PlayerState.INACTIVE)
    override val player1State: StateFlow<ChessClock.PlayerState> = _player1State

    private val _player2State = MutableStateFlow(ChessClock.PlayerState.INACTIVE)
    override val player2State: StateFlow<ChessClock.PlayerState> = _player2State

    private val _activePlayer = MutableStateFlow(ChessClock.Player.NONE)
    override val activePlayer = _activePlayer.asStateFlow()

    private val _gameState = MutableStateFlow(ChessClock.GameState.NOT_STARTED)
    override val gameState = _gameState.asStateFlow()

    init {
        coroutineScope.launch {
            player1Timer.finished.collect { finished ->
                if (finished) {
                   setPlayerOutOfTimeAndGameStateFinished(_player1State)
                }
            }

            player2Timer.finished.collect { finished ->
                if (finished) {
                    setPlayerOutOfTimeAndGameStateFinished(_player2State)
                }
            }
        }
    }

    private fun setPlayerOutOfTimeAndGameStateFinished(playerState: MutableStateFlow<ChessClock.PlayerState>) {
        playerState.value = ChessClock.PlayerState.OUT_OF_TIME
        _gameState.value = ChessClock.GameState.FINISHED
    }

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

        _player1State.value = ChessClock.PlayerState.INACTIVE
        _player2State.value = ChessClock.PlayerState.INACTIVE
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

        _player1State.value = ChessClock.PlayerState.INACTIVE
        _player2State.value = ChessClock.PlayerState.INACTIVE
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
                _player2State.value = ChessClock.PlayerState.ACTIVE
            }
            ChessClock.Player.PLAYER_2 -> {
                player1Timer.start()
                _activePlayer.value = ChessClock.Player.PLAYER_1
                _player1State.value = ChessClock.PlayerState.ACTIVE
            }
            else -> Unit
        }

        _gameState.value = ChessClock.GameState.IN_PROGRESS
    }

    private fun toggleActivePlayer() {
        when (activePlayer.value) {
            ChessClock.Player.PLAYER_1 -> {
                player1Timer.pause()
                player2Timer.start()

                _activePlayer.value = ChessClock.Player.PLAYER_2
                _player2State.value = ChessClock.PlayerState.ACTIVE
                _player1State.value = ChessClock.PlayerState.INACTIVE
            }
            ChessClock.Player.PLAYER_2 -> {
                player2Timer.pause()
                player1Timer.start()

                _activePlayer.value = ChessClock.Player.PLAYER_1
                _player1State.value = ChessClock.PlayerState.ACTIVE
                _player2State.value = ChessClock.PlayerState.INACTIVE
            }
            else -> Unit
        }
    }
}