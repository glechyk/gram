package com.glechyk.gram.delta.authFeature.impl.presentation.model

sealed class TimerUiState {

    data class Start(val timeOfTimer: Long) : TimerUiState()

    data class Tick(val millisUntilFinished: Long) : TimerUiState()

    object Finish : TimerUiState()

}
