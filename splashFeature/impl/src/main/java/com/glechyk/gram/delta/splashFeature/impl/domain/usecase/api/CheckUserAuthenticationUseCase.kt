package com.glechyk.gram.delta.splashFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.splashFeature.impl.domain.model.AuthenticationDomain

interface CheckUserAuthenticationUseCase {

    fun execute(): AuthenticationDomain

}
