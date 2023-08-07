package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.GetUserCachedLoginUseCase

class GetUserCachedLoginUseCaseImpl(
    private val repository: AuthRepository
) : GetUserCachedLoginUseCase {

    override fun execute() = repository.getUserCachedLogin()

}
