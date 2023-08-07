package com.glechyk.gram.delta.splashFeature.impl.domain.repository

import com.glechyk.gram.delta.splashFeature.impl.domain.model.AuthenticationDomain

interface SplashRepository {

    fun isUserAuthenticated(): AuthenticationDomain

}
