package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveTimerStateUseCase

class ObserveTimerStateUseCaseImpl(
    private val repository: ValidationRepository
) : ObserveTimerStateUseCase {

    override fun execute() = repository.timerFlow

}
