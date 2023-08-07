package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObservePasswordConfirmationUseCase

class ObservePasswordConfirmationUseCaseImpl(
    private val repository: ValidationRepository
) : ObservePasswordConfirmationUseCase {

    override fun execute() = repository.passwordConfirmationFlow

}
