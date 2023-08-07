package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedEmailUseCase

class ObserveCachedEmailUseCaseImpl(
    private val repository: AuthRepository
) : ObserveCachedEmailUseCase {

    override fun execute() = repository.emailFlow

}
