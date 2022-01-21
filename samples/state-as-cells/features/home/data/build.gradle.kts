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

    compileOptions {
        sourceCompatibility = Config.Build.javaVersion
        targetCompatibility = Config.Build.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.Build.javaVersion.toString()
    }
}

dependencies {
    implementation(project(Config.Deps.Libs.baseCore))
    implementation(project(Config.Deps.Libs.homeCore))
    implementation(project(Config.Deps.Libs.storiesCore))
    implementation(project(Config.Deps.Libs.newsCore))
}