import org.gradle.api.JavaVersion

object Config {

    object Build {
        const val kotlinVersion = "1.6.10"

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
            const val appcompat = "androidx.appcompat:appcompat:1.4.1"
            const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
            const val recycler = "androidx.recyclerview:recyclerview:1.2.1"
            const val core = "androidx.core:core-ktx:1.7.0"
            private const val lifecycleVersion = "2.4.0"
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
            const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        }

        object Material {
            const val material = "com.google.android.material:material:1.5.0"
        }

        object Coroutines {
            private const val version = "1.6.0"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }

        object CompositeAdapter {
            val compositeAdapter = Publishing.run {
                "${compositeAdapterGroupId}:${compositeAdapter}:${compositeAdapterVersion}"
            }
        }

        object Koin {
            private const val version = "3.1.5"
            const val core = "io.insert-koin:koin-core:$version"
            const val android = "io.insert-koin:koin-android:$version"
        }

        object Kotlin {
            const val kotlinJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Build.kotlinVersion}"
        }

        object Libs {
            const val compositeAdapter = ":composite-adapter"

            const val decorations = ":samples:decorations"
            const val differentBindings = ":samples:different-bindings"
            const val innerRecyclerview = ":samples:inner-recyclerview"

            const val stateAsCells = ":samples:state-as-cells:app"
            const val baseCore = ":samples:state-as-cells:features:base:core"
            const val baseUI = ":samples:state-as-cells:features:base:ui"
            const val homeData = ":samples:state-as-cells:features:home:data"
            const val homeCore = ":samples:state-as-cells:features:home:core"
            const val homeUI = ":samples:state-as-cells:features:home:ui"
            const val storiesCore = ":samples:state-as-cells:features:stories:core"
            const val storiesUI = ":samples:state-as-cells:features:stories:ui"
            const val newsCore = ":samples:state-as-cells:features:news:core"
            const val newsUI = ":samples:state-as-cells:features:news:ui"
        }
    }
}