package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsEmailValidLocalUseCase

class IsEmailValidLocalUseCaseImpl(
    private val repository: ValidationRepository
) : IsEmailValidLocalUseCase {

    override suspend fun execute(email: String) = repository.validateLocalEmail(email)

}
