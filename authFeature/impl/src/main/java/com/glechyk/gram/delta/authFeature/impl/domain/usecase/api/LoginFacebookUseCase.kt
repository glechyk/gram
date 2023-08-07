package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.core.utils.ResultDomain

interface LoginFacebookUseCase {

    suspend fun execute(token: String): ResultDomain<Unit>

}
