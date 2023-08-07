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
}

android {
    namespace = "com.glechyk.gram.delta.feedPageFeature.impl"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.uikit))

    implementation(project(Modules.feedPageFeatureApi))
    implementation(project(Modules.networkApi))

    implementation(Dependencies.coreKtx)
    ui()
    di()
    navigation()
    lifecycleWithViewModel()
    rest()
    testing()
}
