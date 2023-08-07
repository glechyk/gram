package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsFullNameValidUseCase

class IsFullNameValidUseCaseImpl(
    private val repository: ValidationRepository
) : IsFullNameValidUseCase {

    override suspend fun execute(fullName: String) = repository.validateFullName(fullName)

}
