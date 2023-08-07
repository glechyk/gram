package com.glechyk.gram.delta.authFeature.impl.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.glechyk.gram.delta.authFeature.api.AuthFeatureNavigator

class AuthFeatureNavigatorImpl : AuthFeatureNavigator {

    override val authFeatureDeepLink = NavDeepLinkRequest.Builder
        .fromUri(DEEP_LINK.toUri())
        .build()

    private companion object {
        const val DEEP_LINK = "interngram://com.chi.interngram.delta.authFeature/fragment_auth"
    }

}
