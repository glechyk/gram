import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.dsl.DependencyHandler

object Plugins {
    const val application = "com.android.application"
    const val kotlinAndroid = "android"
    const val kotlinKapt = "kapt"
    const val parcelize = "kotlin-parcelize"
    const val navigation = "androidx.navigation.safeargs.kotlin"
    const val ktlint =
        "org.jlleitschuh.gradle.ktlint" // to check the Kotlin code style according the Code Convention
    const val common = "common"
}

object Dependencies {

    // Kotlin core
    val coreKtx by lazy { "androidx.core:core-ktx:1.9.0" }

    // UI
    val appcompat by lazy { "androidx.appcompat:appcompat:1.6.0" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:1.5.5" }
    val material by lazy { "com.google.android.material:material:1.8.0" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:2.1.4" }

    fun DependencyHandler.ui() {
        implementation(appcompat)
        implementation(fragmentKtx)
        implementation(material)
        implementation(constraintLayout)
    }

    // Google navigation
    val navigationFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:2.6.0-alpha04" }
    val navigationUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:2.6.0-alpha04" }

    fun DependencyHandler.navigation() {
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
    }

    // ViewModel and Lifecycle
    val lifecycleProcess by lazy { "androidx.lifecycle:lifecycle-process:2.5.1" }
    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1" }
    val lifecycleViewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1" }

    fun DependencyHandler.lifecycleWithViewModel() {
        implementation(lifecycleProcess)
        implementation(lifecycleRuntimeKtx)
        implementation(lifecycleViewModelKtx)
    }

    // DI
    val koin by lazy { "io.insert-koin:koin-android:3.3.2" }

    fun DependencyHandler.di() {
        implementation(koin)
    }

    // Rest api libs
    val gson by lazy { "com.google.code.gson:gson:2.9.0" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:2.9.0" }
    val converterGson by lazy { "com.squareup.retrofit2:converter-gson:2.9.0" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:5.0.0-alpha.2" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2" }

    fun DependencyHandler.rest() {
        implementation(gson)
        implementation(retrofit)
        implementation(converterGson)
        implementation(okhttp)
        implementation(loggingInterceptor)
    }

    // Local Database
    val kaptRoomCompiler by lazy { "androidx.room:room-compiler:2.5.0" }
    val roomRuntime by lazy { "androidx.room:room-runtime:2.5.0" }
    val roomKtx by lazy { "androidx.room:room-ktx:2.5.0" }

    fun DependencyHandler.database() {
        kapt(kaptRoomCompiler)
        implementation(roomRuntime)
        implementation(roomKtx)
    }

    // Pictures
    val kaptGlideCompiler by lazy { "com.github.bumptech.glide:compiler:4.13.0" }
    val glide by lazy { "com.github.bumptech.glide:glide:4.13.0" }
    val glideOkhttp3Integration by lazy { "com.github.bumptech.glide:okhttp3-integration:4.13.0" }

    fun DependencyHandler.pictures() {
        kapt(kaptGlideCompiler)
        implementation(glide)
        implementation(glideOkhttp3Integration)
    }

    // Google SignIn
    val googlePlayServicesAuth by lazy { "com.google.android.gms:play-services-auth:20.4.1" }
    val googlePeopleApi by lazy { "com.google.apis:google-api-services-people:v1-rev20220531-2.0.0" }
    val googleApiClient by lazy { "com.google.api-client:google-api-client:2.0.0" }
    val googleApiClientAndroid by lazy { "com.google.api-client:google-api-client-android:1.32.1" }

    fun DependencyHandler.googleSignIn() {
        implementation(googlePlayServicesAuth)
        implementation(googlePeopleApi)
        implementation(googleApiClient)
        implementation(googleApiClientAndroid)
    }

    // Testing
    val junit by lazy { "junit:junit:4.13.2" }
    val androidJunit by lazy { "androidx.test.ext:junit:1.1.5" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:3.5.1" }

    fun DependencyHandler.testing() {
        testImplementation(junit)
        androidTestImplementation(androidJunit)
        androidTestImplementation(espressoCore)
    }

    //Facebook
    val facebookSdk by lazy { "com.facebook.android:facebook-android-sdk:latest.release" }
    val facebookLogin by lazy { "com.facebook.android:facebook-login:latest.release" }

    fun DependencyHandler.facebook() {
        implementation(facebookSdk)
        implementation(facebookLogin)
    }

    private fun DependencyHandler.implementation(depName: String) {
        add("implementation", depName)
    }

    private fun DependencyHandler.kapt(depName: String) {
        add("kapt", depName)
    }

    private fun DependencyHandler.testImplementation(depName: String) {
        add("testImplementation", depName)
    }

    private fun DependencyHandler.androidTestImplementation(depName: String) {
        add("androidTestImplementation", depName)
    }
}

object Modules {
    //app module
    const val app = ":app"

    //core module
    const val core = ":core"

    //uikit module
    const val uikit = ":uikit"

    //network module
    const val networkApi = ":network:api"
    const val networkImpl = ":network:impl"

    //splashFeature module
    const val splashFeatureApi = ":splashFeature:api"
    const val splashFeatureImpl = ":splashFeature:impl"

    //authFeature module
    const val authFeatureApi = ":authFeature:api"
    const val authFeatureImpl = ":authFeature:impl"

    //mainFeature module
    const val mainFeatureApi = ":mainFeature:api"
    const val mainFeatureImpl = ":mainFeature:impl"

    //feedPageFeature module
    const val feedPageFeatureApi = ":feedPageFeature:api"
    const val feedPageFeatureImpl = ":feedPageFeature:impl"

    //searchPageFeature module
    const val searchPageFeatureApi = ":searchPageFeature:api"
    const val searchPageFeatureImpl = ":searchPageFeature:impl"

    //newPostPageFeature module
    const val newPostPageFeatureApi = ":newPostPageFeature:api"
    const val newPostPageFeatureImpl = ":newPostPageFeature:impl"

    //notificationsPageFeature module
    const val notificationsPageFeatureApi = ":notificationsPageFeature:api"
    const val notificationsPageFeatureImpl = ":notificationsPageFeature:impl"

    //notificationsPageFeature module
    const val userPageFeatureApi = ":userPageFeature:api"
    const val userPageFeatureImpl = ":userPageFeature:impl"

}

object ConfigData {
    const val NAMESPACE = "com.glechyk.gram.delta"
    const val APPLICATION_ID = "com.chi.interngram.delta"
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val MIN_SDK = 28
    const val TARGET_SDK = 33
    const val COMPILE_SDK = 33
    val JAVA = JavaVersion.VERSION_1_8
}
