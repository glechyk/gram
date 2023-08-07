package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheEmailUseCase

class CacheEmailUseCaseImpl(
    private val repository: AuthRepository
) : CacheEmailUseCase {

    override suspend fun execute(email: String?) = repository.cacheEmail(email)

}
