package com.glechyk.gram.delta.splashFeature.impl.presentation.model

sealed class AuthenticationPresentation {
    object UserIsRegistered : AuthenticationPresentation()
    object UserIsNotRegistered : AuthenticationPresentation()
}
