package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginGoogleUseCase

class LoginGoogleUseCaseImpl(
    private val repository: AuthRepository
): LoginGoogleUseCase {

    override suspend fun execute(
        gmail: String,
        googleId: String,
        fullName: String,
        dateOfBirthday: Long
    ) = repository.logInGoogle(gmail, googleId, fullName, dateOfBirthday)
}
