package com.glechyk.gram.delta.newPostPageFeature.impl.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.glechyk.gram.delta.newPostPageFeature.api.NewPostPageFeatureNavigator

class NewPostPageFeatureNavigatorImpl : NewPostPageFeatureNavigator {

    override val newPostPageFeatureDeepLink = NavDeepLinkRequest.Builder
        .fromUri(DEEP_LINK.toUri())
        .build()

    private companion object {
        const val DEEP_LINK = "interngram://com.chi.interngram.delta.mainFeature/fragment_new_post"
    }
}
