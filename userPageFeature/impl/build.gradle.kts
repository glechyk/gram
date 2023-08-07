import Dependencies.di
import Dependencies.lifecycleWithViewModel
import Dependencies.navigation
import Dependencies.rest
import Dependencies.testing
import Dependencies.ui

plugins {
    id(Plugins.common)
    id(Plugins.navigation)
    id(Plugins.ktlint) version "10.0.0"
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.glechyk.gram.delta.userPageFeature.impl"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.uikit))

    implementation(project(Modules.userPageFeatureApi))
    implementation(project(Modules.networkApi))

    implementation(Dependencies.coreKtx)
    ui()
    di()
    navigation()
    lifecycleWithViewModel()
    rest()
    testing()
}
