import org.gradle.api.JavaVersion

object Config {

    object Build {
        const val kotlinVersion = "1.6.0"

        const val compileSdk = 31
        const val minSdk = 17
        const val sampleMinSdk = 21
        const val targetSdk = compileSdk

        val javaVersion = JavaVersion.VERSION_11

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
            const val core = "androidx.core:core-ktx:1.6.0"
            private const val lifecycleVersion = "2.4.0"
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
            const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        }

        object Material {
            const val material = "com.google.android.material:material:1.4.0"
        }

        object Coroutines {
            private const val version = "1.5.2"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }

        object CompositeAdapter {
            val compositeAdapter = Publishing.run {
                "${compositeAdapterGroupId}:${compositeAdapter}:${compositeAdapterVersion}"
            }
        }

        object Koin {
            private const val version = "3.1.2"
            const val core = "io.insert-koin:koin-core:$version"
            const val android = "io.insert-koin:koin-android:$version"
        }

        object Kotlin {
            const val kotlinJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Build.kotlinVersion}"
        }

        object Libs {
            const val compositeAdapter = ":composite-adapter"

            const val baseCore = ":sample:features:base:core"
            const val baseUI = ":sample:features:base:ui"

            const val messagesData = ":sample:features:messages:data"
            const val messagesCore = ":sample:features:messages:core"
            const val messagesUI = ":sample:features:messages:ui"
        }
    }
}