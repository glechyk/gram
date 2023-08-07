pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "Gram"
include(":app")
include(":core")
include(":uikit")
include(":network:api")
include(":network:impl")
include(":splashFeature:api")
include(":splashFeature:impl")
include(":authFeature:api")
include(":authFeature:impl")
include(":mainFeature:api")
include(":mainFeature:impl")
include(":feedPageFeature:api")
include(":feedPageFeature:impl")
include(":searchPageFeature:api")
include(":searchPageFeature:impl")
include(":newPostPageFeature:api")
include(":newPostPageFeature:impl")
include(":notificationsPageFeature:api")
include(":notificationsPageFeature:impl")
include(":userPageFeature:api")
include(":userPageFeature:impl")
