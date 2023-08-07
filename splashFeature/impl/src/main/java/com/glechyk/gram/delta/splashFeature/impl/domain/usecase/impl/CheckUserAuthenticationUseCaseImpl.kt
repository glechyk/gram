package com.glechyk.gram.delta.splashFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.splashFeature.impl.domain.repository.SplashRepository
import com.glechyk.gram.delta.splashFeature.impl.domain.usecase.api.CheckUserAuthenticationUseCase

class CheckUserAuthenticationUseCaseImpl(
    private val repository: SplashRepository
) : CheckUserAuthenticationUseCase {
    override fun execute() = repository.isUserAuthenticated()
}
