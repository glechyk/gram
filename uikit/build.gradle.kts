import Dependencies.testing
import Dependencies.ui

plugins {
    id(Plugins.common)
    id(Plugins.ktlint) version "10.0.0"
}

android {
    namespace = "com.glechyk.gram.delta.uikit"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(Dependencies.coreKtx)
    ui()
    testing()
}
