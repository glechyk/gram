package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.core.utils.ResultDomain

interface LoginGoogleUseCase {

    suspend fun execute(
        gmail: String,
        googleId: String,
        fullName: String,
        dateOfBirthday: Long
    ): ResultDomain<Unit>

}
