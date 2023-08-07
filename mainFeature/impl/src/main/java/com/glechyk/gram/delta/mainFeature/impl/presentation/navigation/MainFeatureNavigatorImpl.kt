package com.glechyk.gram.delta.mainFeature.impl.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.glechyk.gram.delta.mainFeature.api.MainFeatureNavigator

class MainFeatureNavigatorImpl : MainFeatureNavigator {

    override val mainFeatureDeepLink = NavDeepLinkRequest.Builder
        .fromUri(DEEP_LINK.toUri())
        .build()

    private companion object {
        const val DEEP_LINK = "interngram://com.chi.interngram.delta.mainFeature/fragment_main"
    }
}
