package com.glechyk.gram.delta.splashFeature.impl.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.glechyk.gram.delta.splashFeature.api.SplashFeatureNavigator

class SplashFeatureNavigatorImpl : SplashFeatureNavigator {

    override val splashFeatureDeepLink = NavDeepLinkRequest.Builder
        .fromUri(DEEP_LINK.toUri())
        .build()

    private companion object {
        const val DEEP_LINK = "interngram://com.chi.interngram.delta.splashFeature/fragment_splash"
    }

}
