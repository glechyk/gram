package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.SendVerificationCodeUseCase

class SendVerificationCodeUseCaseImpl(
    private val repository: AuthRepository
) : SendVerificationCodeUseCase {

    override suspend fun execute() = repository.sendVerificationCode()

}
