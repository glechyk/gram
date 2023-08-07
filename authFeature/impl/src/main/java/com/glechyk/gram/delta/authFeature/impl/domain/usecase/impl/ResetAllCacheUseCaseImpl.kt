package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ResetAllCacheUseCase

class ResetAllCacheUseCaseImpl(
    private val repository: AuthRepository
) : ResetAllCacheUseCase {

    override suspend fun execute() = repository.resetAllCache()

}
