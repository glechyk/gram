package com.glechyk.gram.delta.userPageFeature.impl.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.glechyk.gram.delta.userPageFeature.api.UserPageFeatureNavigator

class UserPageFeatureNavigatorImpl : UserPageFeatureNavigator {
    override val userPageFeatureDeepLink = NavDeepLinkRequest.Builder
        .fromUri(DEEP_LINK.toUri())
        .build()

    private companion object {
        const val DEEP_LINK =
            "interngram://com.chi.interngram.delta.mainFeature/fragment_user_page"
    }
}
