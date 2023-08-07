package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedFullNameUseCase

class ObserveCachedFullNameUseCaseImpl(
    private val repository: AuthRepository
) : ObserveCachedFullNameUseCase {

    override fun execute() = repository.fullNameFlow

}
