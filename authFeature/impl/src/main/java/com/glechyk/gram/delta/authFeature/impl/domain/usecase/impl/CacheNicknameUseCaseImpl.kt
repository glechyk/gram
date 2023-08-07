package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheNicknameUseCase

class CacheNicknameUseCaseImpl(
    private val repository: AuthRepository
) : CacheNicknameUseCase {

    override suspend fun execute(nickname: String?) = repository.cacheNickname(nickname)

}
