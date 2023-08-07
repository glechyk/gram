import Dependencies.di
import Dependencies.lifecycleWithViewModel
import Dependencies.navigation
import Dependencies.testing
import Dependencies.ui

plugins {
    id(Plugins.common)
    id(Plugins.navigation)
    id(Plugins.ktlint) version "10.0.0"
}

android {
    namespace = "com.glechyk.gram.delta.splashFeature.impl"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.uikit))
    implementation(project(Modules.splashFeatureApi))

    implementation(project(Modules.authFeatureApi))
    implementation(project(Modules.mainFeatureApi))

    implementation(Dependencies.coreKtx)
    ui()
    di()
    navigation()
    lifecycleWithViewModel()
    testing()

}
