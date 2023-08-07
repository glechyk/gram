package com.glechyk.gram.delta.searchPageFeature.impl.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.glechyk.gram.delta.searchPageFeature.api.SearchPageFeatureNavigator

class SearchPageFeatureNavigatorImpl : SearchPageFeatureNavigator {

    override val searchPageFeatureDeepLink = NavDeepLinkRequest.Builder
        .fromUri(DEEP_LINK.toUri())
        .build()

    private companion object {
        const val DEEP_LINK = "interngram://com.chi.interngram.delta.mainFeature/fragment_search_page"
    }
}
