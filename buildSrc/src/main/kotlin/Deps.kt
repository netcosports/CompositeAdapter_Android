import org.gradle.api.JavaVersion

object Config {

    object Build {
        const val kotlinVersion = "1.6.0"

        const val compileSdk = 31
        const val minSdk = 17
        const val sampleMinSdk = 21
        const val targetSdk = compileSdk

        val javaVersion = JavaVersion.VERSION_1_8

        const val packageNameDev = "io.github.netcosports.compositeadapter.sample"
        const val packageNameProd = "io.github.netcosports.compositeadapter.sample"

        const val versionName = "1.0.1"
        const val versionOffset = 0
    }

    object Publishing {
        const val compositeAdapterGroupId = "io.github.netcosports.compositeadapter"
        const val compositeAdapterVersion = Build.versionName //"1.0.0"

        const val compositeAdapter = "composite-adapter"
    }

    object Plugins {
        const val buildGradle = "com.android.tools.build:gradle:7.0.3"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Build.kotlinVersion}"
        const val androidApp = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val android = "android"
        const val kapt = "kapt"
    }

    object Deps {
        object AndroidX {
            const val appcompat = "androidx.appcompat:appcompat:1.4.0"
            const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
            const val recycler = "androidx.recyclerview:recyclerview:1.2.1"
            const val lifecycleVersion = "2.4.0"
            const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        }

        object Coroutines {
            const val version = "1.5.2"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }

        object CompositeAdapter {
            val compositeAdapter = Publishing.run {
                "${compositeAdapterGroupId}:${compositeAdapter}:${compositeAdapterVersion}"
            }
        }

        object Kotlin {
            const val kotlinJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Build.kotlinVersion}"
        }

        object Libs {
            const val compositeAdapter = ":composite-adapter"
        }
    }
}