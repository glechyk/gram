package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsVerificationCodeConfirmedUseCase

class IsVerificationCodeConfirmedUseCaseImpl(
    private val repository: AuthRepository
) : IsVerificationCodeConfirmedUseCase {
    override suspend fun execute(code: String) = repository.confirmCode(code)
}
