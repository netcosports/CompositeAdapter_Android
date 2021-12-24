plugins {
    id(Config.Plugins.androidLibrary)
    kotlin(Config.Plugins.android)
}

android {
    compileSdk = Config.Build.compileSdk

    defaultConfig {
        minSdk = Config.Build.sampleMinSdk
        targetSdk = Config.Build.targetSdk
    }

    buildTypes {
        getByName("debug") {
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    compileOptions {
        sourceCompatibility = Config.Build.javaVersion
        targetCompatibility = Config.Build.javaVersion
    }

    kotlinOptions {
        jvmTarget = Config.Build.javaVersion.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Config.Deps.Kotlin.kotlinJdk8)

    implementation(Config.Deps.AndroidX.appcompat)
    implementation(Config.Deps.AndroidX.swipeRefresh)
    implementation(Config.Deps.AndroidX.recycler)

//    implementation(Config.Deps.CompositeAdapter.compositeAdapter)
    implementation(project(Config.Deps.Libs.compositeAdapter))
}