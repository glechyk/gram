package com.glechyk.gram.delta

import android.app.Application
import com.glechyk.gram.delta.authFeature.impl.di.authFeatureModule
import com.glechyk.gram.delta.di.appModule
import com.glechyk.gram.delta.di.navigationModule
import com.glechyk.gram.delta.di.networkModule
import com.glechyk.gram.delta.splashFeature.impl.di.splashFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class InterngramApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@InterngramApp)
            modules(
                appModule,
                navigationModule,
                networkModule,
                splashFeatureModule,
                authFeatureModule
            )
        }
    }
}
