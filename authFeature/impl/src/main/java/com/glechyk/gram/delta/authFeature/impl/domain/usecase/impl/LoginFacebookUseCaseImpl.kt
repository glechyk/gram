package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginFacebookUseCase

class LoginFacebookUseCaseImpl(
    private val repository: AuthRepository
) : LoginFacebookUseCase {

    override suspend fun execute(token: String) =
        repository.logInFacebook(token)

}
