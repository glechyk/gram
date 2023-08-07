package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPhoneNumberValidNetworkUseCase

class IsPhoneNumberValidNetworkUseCaseImpl(
    private val repository: ValidationRepository
) : IsPhoneNumberValidNetworkUseCase {

    override suspend fun execute(phoneNumber: String) =
        repository.validateNetworkPhoneNumber(phoneNumber)

}
