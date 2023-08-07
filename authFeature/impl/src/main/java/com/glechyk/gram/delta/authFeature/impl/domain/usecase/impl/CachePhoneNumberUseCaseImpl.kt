package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CachePhoneNumberUseCase

class CachePhoneNumberUseCaseImpl(
    private val repository: AuthRepository
) : CachePhoneNumberUseCase {

    override suspend fun execute(phoneNumber: String?) = repository.cachePhoneNumber(phoneNumber)

}
