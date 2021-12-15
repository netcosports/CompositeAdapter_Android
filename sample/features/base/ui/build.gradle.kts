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
    implementation(project(Config.Deps.Libs.baseCore))
    api(Config.Deps.Coroutines.android)

    api(Config.Deps.AndroidX.appcompat)
    api(Config.Deps.AndroidX.recycler)
    api(Config.Deps.AndroidX.core)
    api(Config.Deps.AndroidX.swipeRefresh)
    api(Config.Deps.AndroidX.lifecycle)
    api(Config.Deps.AndroidX.lifecycleViewModelKtx)
    api(Config.Deps.AndroidX.lifecycleLiveDataKtx)
    api(Config.Deps.Material.material)

    api(Config.Deps.Koin.android)

//    api(Config.Deps.CompositeAdapter.compositeAdapter)
    api(project(Config.Deps.Libs.compositeAdapter))
}