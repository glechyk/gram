package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPhoneNumberValidLocalUseCase

class IsPhoneNumberValidLocalUseCaseImpl(
    private val repository: ValidationRepository
) : IsPhoneNumberValidLocalUseCase {

    override suspend fun execute(phoneNumber: String) = repository.validateLocalPhoneNumber(phoneNumber)

}
