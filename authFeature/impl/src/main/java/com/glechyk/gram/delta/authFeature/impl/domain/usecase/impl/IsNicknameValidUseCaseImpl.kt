package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsNicknameValidUseCase

class IsNicknameValidUseCaseImpl(
    private val repository: ValidationRepository
) : IsNicknameValidUseCase {

    override suspend fun execute(nickname: String) = repository.validateNickname(nickname)

}
