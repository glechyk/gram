package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheFullNameUseCase

class CacheFullNameUseCaseImpl(
    private val repository: AuthRepository
) : CacheFullNameUseCase {

    override suspend fun execute(fullName: String?) = repository.cacheFullName(fullName)

}
