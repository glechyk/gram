package com.glechyk.gram.delta.splashFeature.impl.presentation.mappers

import com.glechyk.gram.delta.splashFeature.impl.domain.model.AuthenticationDomain

fun AuthenticationDomain.toAuthenticationPresentation() = when (this) {
    is AuthenticationDomain.UserIsRegistered -> com.glechyk.gram.delta.splashFeature.impl.presentation.model.AuthenticationPresentation.UserIsRegistered
    is AuthenticationDomain.UserIsNotRegistered -> com.glechyk.gram.delta.splashFeature.impl.presentation.model.AuthenticationPresentation.UserIsNotRegistered
}
