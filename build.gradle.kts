// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0-alpha04")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}