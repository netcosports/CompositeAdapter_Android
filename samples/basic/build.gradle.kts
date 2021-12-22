plugins {
    id(Config.Plugins.androidApp)
    kotlin(Config.Plugins.android)
}

android {
    compileSdk = Config.Build.compileSdk

    defaultConfig {
        minSdk = Config.Build.sampleMinSdk
        targetSdk = Config.Build.targetSdk

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

    flavorDimensions.add("env")
    productFlavors {
        create("development") {
            dimension = "env"
            applicationId = Config.Build.packageNameDev
        }

        create("production") {
            dimension = "env"
            applicationId = Config.Build.packageNameProd
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
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(Config.Deps.Libs.baseUI))
    implementation(project(Config.Deps.Libs.messagesCore))
    implementation(project(Config.Deps.Libs.messagesUI))
}