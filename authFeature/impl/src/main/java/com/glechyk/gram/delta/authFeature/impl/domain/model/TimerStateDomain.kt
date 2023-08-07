package com.glechyk.gram.delta.authFeature.impl.domain.model

sealed class TimerStateDomain {

    data class Start(val timeOfTimer: Long) : TimerStateDomain()

    data class Tick(val millisUntilFinished: Long) : TimerStateDomain()

    object Finish : TimerStateDomain()

}
