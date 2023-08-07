package com.glechyk.gram.delta.splashFeature.impl.domain.model

sealed class AuthenticationDomain {
    object UserIsRegistered : AuthenticationDomain()
    object UserIsNotRegistered : AuthenticationDomain()
}
