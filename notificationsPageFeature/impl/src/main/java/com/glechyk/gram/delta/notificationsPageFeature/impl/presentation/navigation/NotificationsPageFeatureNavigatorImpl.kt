package com.glechyk.gram.delta.notificationsPageFeature.impl.presentation.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.glechyk.gram.delta.notificationsPageFeature.api.NotificationsPageFeatureNavigator

class NotificationsPageFeatureNavigatorImpl : NotificationsPageFeatureNavigator {
    override val notificationsPageFeatureDeepLink = NavDeepLinkRequest.Builder
        .fromUri(DEEP_LINK.toUri())
        .build()

    private companion object {
        const val DEEP_LINK = "interngram://com.chi.interngram.delta.mainFeature/fragment_notifications"
    }
}
