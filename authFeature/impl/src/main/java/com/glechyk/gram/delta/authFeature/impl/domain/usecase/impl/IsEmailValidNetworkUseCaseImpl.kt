package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsEmailValidNetworkUseCase

class IsEmailValidNetworkUseCaseImpl(
    private val repository: ValidationRepository
) : IsEmailValidNetworkUseCase {

    override suspend fun execute(email: String) = repository.validateNetworkEmail(email)

}
