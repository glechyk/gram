import Dependencies.navigation
import Dependencies.testing

plugins {
    id(Plugins.common)
    id(Plugins.navigation)
    id(Plugins.ktlint) version "10.0.0"
}

android {
    namespace = "com.glechyk.gram.delta.splashFeature.api"
}

dependencies {
    implementation(Dependencies.coreKtx)
    navigation()
    testing()
}
