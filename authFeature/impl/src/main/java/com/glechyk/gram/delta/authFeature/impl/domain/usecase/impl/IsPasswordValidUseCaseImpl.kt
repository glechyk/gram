package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPasswordValidUseCase

class IsPasswordValidUseCaseImpl(
    private val repository: ValidationRepository
) : IsPasswordValidUseCase {

    override suspend fun execute(password: String) = repository.validatePassword(password)

}
