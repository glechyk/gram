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
    namespace = "com.glechyk.gram.delta.mainFeature.impl"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.uikit))

    implementation(project(Modules.mainFeatureApi))
    implementation(project(Modules.networkApi))

    implementation(project(Modules.feedPageFeatureImpl))
    implementation(project(Modules.searchPageFeatureImpl))
    implementation(project(Modules.newPostPageFeatureImpl))
    implementation(project(Modules.notificationsPageFeatureImpl))
    implementation(project(Modules.userPageFeatureImpl))

    implementation(Dependencies.coreKtx)
    ui()
    di()
    navigation()
    lifecycleWithViewModel()
    rest()
    testing()
}
