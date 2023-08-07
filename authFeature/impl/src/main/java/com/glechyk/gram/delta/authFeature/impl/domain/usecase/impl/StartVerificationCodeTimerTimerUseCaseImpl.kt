package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.StartVerificationCodeTimerUseCase

class StartVerificationCodeTimerTimerUseCaseImpl(
    private val repository: ValidationRepository
) : StartVerificationCodeTimerUseCase {

    override fun execute(timeOfTimer: Long) = repository.startTimer(timeOfTimer)

}
