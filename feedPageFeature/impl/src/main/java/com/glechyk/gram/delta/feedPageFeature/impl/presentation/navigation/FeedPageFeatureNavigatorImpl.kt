package com.glechyk.gram.delta.feedPageFeature.impl.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.glechyk.gram.delta.feedPageFeature.api.FeedPageFeatureNavigator

class FeedPageFeatureNavigatorImpl : FeedPageFeatureNavigator {

    override val feedPageFeatureDeepLink = NavDeepLinkRequest.Builder
        .fromUri(DEEP_LINK.toUri())
        .build()

    private companion object {
        const val DEEP_LINK = "interngram://com.chi.interngram.delta.mainFeature/fragment_feed_page"
    }
}
