package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObservePasswordValidationUseCase

class ObservePasswordValidationUseCaseImpl(
    private val repository: ValidationRepository
) : ObservePasswordValidationUseCase {

    override fun execute() = repository.passwordValidationFlow

}
