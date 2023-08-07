import Dependencies.di
import Dependencies.facebook
import Dependencies.lifecycleWithViewModel
import Dependencies.navigation
import Dependencies.rest
import Dependencies.testing
import Dependencies.ui

plugins {
    id(Plugins.application)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.parcelize)
    id(Plugins.navigation)
    id(Plugins.ktlint) version "10.0.0"
}

android {
    namespace = ConfigData.NAMESPACE
    compileSdk = ConfigData.COMPILE_SDK

    defaultConfig {
        applicationId = ConfigData.APPLICATION_ID
        minSdk = ConfigData.MIN_SDK
        targetSdk = ConfigData.TARGET_SDK
        versionCode = ConfigData.VERSION_CODE
        versionName = ConfigData.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            buildConfigField(
                "String",
                "AUTH_BASE_URL",
                "\"https://4b3f0x4wzj.execute-api.eu-west-3.amazonaws.com/\""
            )
            buildConfigField(
                "String",
                "MAIN_BASE_URL",
                "\"http://delta1.chisw.com/\""
            )
            buildConfigField(
                "String",
                "WEB_CLIENT_ID",
                "\"WEB_CLIENT_ID\""
            )
            resValue(
                "string",
                "FACEBOOK_APP_ID",
                "FACEBOOK_APP_ID"
            )
            resValue(
                "string",
                "FACEBOOK_CLIENT_TOKEN",
                "FACEBOOK_CLIENT_TOKEN"
            )
        }
    }

    compileOptions {
        sourceCompatibility = ConfigData.JAVA
        targetCompatibility = ConfigData.JAVA
    }

    kotlinOptions {
        jvmTarget = ConfigData.JAVA.toString()
    }

    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.uikit))

    //network module
    implementation(project(Modules.networkApi))
    implementation(project(Modules.networkImpl))

    //splashFeature module
    implementation(project(Modules.splashFeatureApi))
    implementation(project(Modules.splashFeatureImpl))

    //authFeature module
    implementation(project(Modules.authFeatureApi))
    implementation(project(Modules.authFeatureImpl))

    //mainFeature module
    implementation(project(Modules.mainFeatureApi))
    implementation(project(Modules.mainFeatureImpl))

    //feedPageFeature module
    implementation(project(Modules.feedPageFeatureApi))
    implementation(project(Modules.feedPageFeatureImpl))

    //searchPageFeature module
    implementation(project(Modules.searchPageFeatureApi))
    implementation(project(Modules.searchPageFeatureImpl))

    //newPostPageFeature module
    implementation(project(Modules.newPostPageFeatureApi))
    implementation(project(Modules.newPostPageFeatureImpl))

    //notificationsPageFeature module
    implementation(project(Modules.notificationsPageFeatureApi))
    implementation(project(Modules.notificationsPageFeatureImpl))

    //notificationsPageFeature module
    implementation(project(Modules.userPageFeatureApi))
    implementation(project(Modules.userPageFeatureImpl))

    implementation(Dependencies.coreKtx)
    ui()
    di()
    navigation()
    lifecycleWithViewModel()
    rest()
    testing()
    facebook()
}