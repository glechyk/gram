package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CachePasswordUseCase

class CachePasswordUseCaseImpl(
    private val repository: AuthRepository
) : CachePasswordUseCase {

    override suspend fun execute(password: String?) = repository.cachePassword(password)

}
