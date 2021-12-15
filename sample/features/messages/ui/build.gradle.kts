plugins {
    id(Config.Plugins.androidLibrary)
    kotlin(Config.Plugins.android)
    kotlin(Config.Plugins.kapt)
}

android {
    compileSdk = Config.Build.compileSdk

    defaultConfig {
        minSdk = Config.Build.minSdk
        targetSdk = Config.Build.targetSdk
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
}