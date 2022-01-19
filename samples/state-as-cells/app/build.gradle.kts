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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Config.Deps.Libs.baseUI))
    implementation(project(Config.Deps.Libs.homeData))
    implementation(project(Config.Deps.Libs.homeCore))
    api(project(Config.Deps.Libs.homeUI))
    implementation(project(Config.Deps.Libs.storiesCore))
    implementation(project(Config.Deps.Libs.storiesUI))
    implementation(project(Config.Deps.Libs.newsCore))
    implementation(project(Config.Deps.Libs.newsUI))
}