package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheBirthdayUseCase

class CacheBirthdayUseCaseImpl(
    private val repository: AuthRepository
) : CacheBirthdayUseCase {

    override suspend fun execute(date: Long?) = repository.cacheBirthday(date)

}
