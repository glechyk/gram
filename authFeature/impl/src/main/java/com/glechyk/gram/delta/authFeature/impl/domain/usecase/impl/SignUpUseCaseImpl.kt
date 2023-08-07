package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.SignUpUseCase

class SignUpUseCaseImpl(
    private val repository: AuthRepository
) : SignUpUseCase {

    override suspend fun execute() = repository.signUp()

}
