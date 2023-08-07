import Dependencies.rest
import Dependencies.testing
import Dependencies.ui

plugins {
    id(Plugins.common)
    id(Plugins.ktlint) version "10.0.0"
}

android {
    namespace = "com.glechyk.gram.delta.network.impl"
}

dependencies {
    
    implementation(project(Modules.networkApi))
    implementation(project(Modules.core))

    implementation(Dependencies.coreKtx)
    rest()
    testing()
}
