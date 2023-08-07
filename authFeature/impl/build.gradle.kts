import Dependencies.di
import Dependencies.googleSignIn
import Dependencies.facebook
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
    namespace = "com.glechyk.gram.delta.authFeature.impl"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.uikit))

    implementation(project(Modules.authFeatureApi))
    implementation(project(Modules.mainFeatureApi))
    implementation(project(Modules.networkApi))

    implementation(Dependencies.coreKtx)
    ui()
    di()
    googleSignIn()
    facebook()
    navigation()
    lifecycleWithViewModel()
    rest()
    testing()
}
