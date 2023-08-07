package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface StartVerificationCodeTimerUseCase {

    fun execute(timeOfTimer: Long)

}
