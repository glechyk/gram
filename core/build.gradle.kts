import Dependencies.di
import Dependencies.lifecycleWithViewModel
import Dependencies.navigation
import Dependencies.rest
import Dependencies.retrofit
import Dependencies.testing
import Dependencies.ui

plugins {
    id(Plugins.common)
    id(Plugins.navigation)
    id(Plugins.ktlint) version "10.0.0"
}

android {
    namespace = "com.glechyk.gram.delta.core"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.coreKtx)
    ui()
    lifecycleWithViewModel()
    navigation()
    rest()
    di()
    testing()
}
