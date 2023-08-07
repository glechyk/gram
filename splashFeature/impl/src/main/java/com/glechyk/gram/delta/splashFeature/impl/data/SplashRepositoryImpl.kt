package com.glechyk.gram.delta.splashFeature.impl.data

import com.glechyk.gram.delta.splashFeature.impl.domain.model.AuthenticationDomain
import com.glechyk.gram.delta.splashFeature.impl.domain.repository.SplashRepository

class SplashRepositoryImpl : SplashRepository {
    override fun isUserAuthenticated() = AuthenticationDomain.UserIsNotRegistered

}
