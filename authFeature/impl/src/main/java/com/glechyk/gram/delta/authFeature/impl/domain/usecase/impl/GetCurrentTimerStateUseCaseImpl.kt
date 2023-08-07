package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.GetCurrentTimerStateUseCase

class GetCurrentTimerStateUseCaseImpl(
    private val repository: ValidationRepository
) : GetCurrentTimerStateUseCase {

    override fun execute() = repository.getCurrentTimerState()

}
