import org.gradle.api.JavaVersion

object Config {

    object Build {
        const val kotlinVersion = "1.5.20"

        const val compileSdk = 30
        const val buildTools = "30.0.2"
        const val minSdk = 17
        const val sampleMinSdk = 21
        const val targetSdk = compileSdk

        val javaVersion = JavaVersion.VERSION_1_8

        const val repoName = "CompositeAdapter_Android"
        const val packageNameDev = "com.netcosports.composite.adapter.sample"
        const val packageNameProd = "com.netcosports.composite.adapter.sample"

        const val versionName = "1.0.0"
        const val versionOffset = 0
    }

    object Publishing {
        const val compositeAdapterGroupId = "com.netcosports.compositeadapter"
        const val compositeAdapterVersion = Build.versionName //"1.0.0"

        const val compositeAdapter = "composite-adapter"
    }

    object Plugins {
        const val buildGradle = "com.android.tools.build:gradle:4.2.0"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Build.kotlinVersion}"
        const val androidApp = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val android = "android"
        const val kapt = "kapt"
        const val mavenPublish = "maven-publish"
        const val jfrogArtifactory = "com.jfrog.artifactory"
        const val jfrogArtifactoryVersion = "4.21.0"
    }

    object Deps {
        object AndroidX {
            const val appcompat = "androidx.appcompat:appcompat:1.3.0"
            const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
            const val recycler = "androidx.recyclerview:recyclerview:1.2.1"
            const val lifecycleVersion = "2.3.1"
            const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        }

        object Coroutines {
            const val version = "1.5.0"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }

        object CompositeAdapter {
            const val compositeAdapter = "com.netcosports.composite-adapter:${Publishing.compositeAdapterVersion}"
        }

        object Kotlin {
            const val kotlinJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Build.kotlinVersion}"
        }

        object Libs {
            const val compositeAdapter = ":composite-adapter"
        }
    }
}