package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginUseCase

class LoginUseCaseImpl(
    private val repository: AuthRepository
) : LoginUseCase {

    override suspend fun execute(username: String, password: String) =
        repository.logIn(username, password)

}
