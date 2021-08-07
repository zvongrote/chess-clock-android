package com.zachvg.chessclock.di

import com.zachvg.chessclock.clock.ChessClockImpl
import com.zachvg.chessclock.clock.ChessTimerImpl
import com.zachvg.chessclock.domain.ChessClock
import com.zachvg.chessclock.domain.ChessTimer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ClockModule {

    @Binds
    abstract fun bindChessTimer(chessTimerImpl: ChessTimerImpl): ChessTimer

    @Binds
    abstract fun bindChessClock(chessClockImpl: ChessClockImpl): ChessClock
}