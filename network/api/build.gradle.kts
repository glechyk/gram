import Dependencies.testing

plugins {
    id(Plugins.common)
    id(Plugins.ktlint) version "10.0.0"
}

android {
    namespace = "com.glechyk.gram.delta.network.api"
}

dependencies {
    implementation(Dependencies.coreKtx)
    testing()
}
