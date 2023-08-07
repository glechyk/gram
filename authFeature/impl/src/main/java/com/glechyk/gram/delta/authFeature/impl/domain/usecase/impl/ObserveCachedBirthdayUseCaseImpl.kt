package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedBirthdayUseCase

class ObserveCachedBirthdayUseCaseImpl(
    private val repository: AuthRepository
) : ObserveCachedBirthdayUseCase {

    override fun execute() = repository.birthdayFlow

}
