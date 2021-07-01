plugins {
    id(Config.Plugins.androidApp)
    kotlin(Config.Plugins.android)
    kotlin(Config.Plugins.kapt)
}

android {
    compileSdkVersion(Config.Build.compileSdk)
    buildToolsVersion(Config.Build.buildTools)

    defaultConfig {
        minSdkVersion(Config.Build.sampleMinSdk)
        targetSdkVersion(Config.Build.targetSdk)

        versionCode = getCustomVersionCode()
        versionName = getCustomVersionName()
    }

    buildTypes {
        getByName("debug") {
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    flavorDimensions("env")
    productFlavors {
        create("development") {
            dimension("env")
            applicationId = Config.Build.packageNameDev
        }

        create("production") {
            dimension("env")
            applicationId = Config.Build.packageNameProd
        }
    }

    compileOptions {
        sourceCompatibility = Config.Build.javaVersion
        targetCompatibility = Config.Build.javaVersion
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(Config.Deps.Kotlin.kotlinJdk8)
    implementation(Config.Deps.Coroutines.android)

    implementation(Config.Deps.AndroidX.appcompat)
    implementation(Config.Deps.AndroidX.recycler)
    implementation(Config.Deps.AndroidX.swipeRefresh)
    implementation(Config.Deps.AndroidX.lifecycleViewModelKtx)
    implementation(Config.Deps.AndroidX.lifecycleLiveDataKtx)

    implementation(project(Config.Deps.Libs.compositeAdapter))
}