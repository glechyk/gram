package com.glechyk.gram.delta.di

import android.content.Context
import android.content.SharedPreferences
import com.chi.interngram.delta.BuildConfig
import com.glechyk.gram.delta.network.api.Network
import com.glechyk.gram.delta.network.api.TokenManager
import com.glechyk.gram.delta.network.impl.NetworkFactory
import com.glechyk.gram.delta.network.impl.TokenManagerImpl
import com.glechyk.gram.delta.authFeature.api.AuthFeatureNavigator
import com.glechyk.gram.delta.authFeature.impl.presentation.navigation.AuthFeatureNavigatorImpl
import com.glechyk.gram.delta.core.utils.Constants
import com.glechyk.gram.delta.feedPageFeature.api.FeedPageFeatureNavigator
import com.glechyk.gram.delta.feedPageFeature.impl.presentation.navigation.FeedPageFeatureNavigatorImpl
import com.glechyk.gram.delta.mainFeature.api.MainFeatureNavigator
import com.glechyk.gram.delta.mainFeature.impl.presentation.navigation.MainFeatureNavigatorImpl
import com.glechyk.gram.delta.newPostPageFeature.api.NewPostPageFeatureNavigator
import com.glechyk.gram.delta.newPostPageFeature.impl.presentation.navigation.NewPostPageFeatureNavigatorImpl
import com.glechyk.gram.delta.notificationsPageFeature.api.NotificationsPageFeatureNavigator
import com.glechyk.gram.delta.notificationsPageFeature.impl.presentation.navigation.NotificationsPageFeatureNavigatorImpl
import com.glechyk.gram.delta.searchPageFeature.api.SearchPageFeatureNavigator
import com.glechyk.gram.delta.searchPageFeature.impl.presentation.navigation.SearchPageFeatureNavigatorImpl
import com.glechyk.gram.delta.splashFeature.api.SplashFeatureNavigator
import com.glechyk.gram.delta.splashFeature.impl.presentation.navigation.SplashFeatureNavigatorImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            "default",
            Context.MODE_PRIVATE
        )
    }
    factory(named(Constants.WEB_CLIENT_ID)) { BuildConfig.WEB_CLIENT_ID }
}

val networkModule = module {

    factory(named(Constants.AUTH_BASE_URL)) { BuildConfig.AUTH_BASE_URL }
    factory(named(Constants.MAIN_BASE_URL)) { BuildConfig.MAIN_BASE_URL }

    single<TokenManager> {
        TokenManagerImpl(
            sharedPreferences = get()
        )
    }

    single<Network> {
        NetworkFactory(
            tokenManager = get()
        )
    }
}

val navigationModule = module {
    factory<SplashFeatureNavigator> { SplashFeatureNavigatorImpl() }
    factory<AuthFeatureNavigator> { AuthFeatureNavigatorImpl() }
    factory<MainFeatureNavigator> { MainFeatureNavigatorImpl() }
    factory<FeedPageFeatureNavigator> { FeedPageFeatureNavigatorImpl() }
    factory<SearchPageFeatureNavigator> { SearchPageFeatureNavigatorImpl() }
    factory<NewPostPageFeatureNavigator> { NewPostPageFeatureNavigatorImpl() }
    factory<NotificationsPageFeatureNavigator> { NotificationsPageFeatureNavigatorImpl() }
}
