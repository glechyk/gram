package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPasswordConfirmationValidUseCase

class IsPasswordConfirmationValidUseCaseImpl(
    private val repository: ValidationRepository
) : IsPasswordConfirmationValidUseCase {

    override suspend fun execute(password: String, confirmation: String) =
        repository.validatePasswordConfirmation(password, confirmation)

}
