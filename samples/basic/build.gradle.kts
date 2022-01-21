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
        viewBinding = true
        //only for the `different-bindings` module and only if dataBinding is used
        dataBinding = true
    }
}

dependencies {
    implementation(Config.Deps.Kotlin.kotlinJdk8)

    implementation(Config.Deps.AndroidX.appcompat)
    implementation(Config.Deps.AndroidX.recycler)
    implementation(Config.Deps.Material.material)

//    implementation(Config.Deps.CompositeAdapter.compositeAdapter)
    implementation(project(Config.Deps.Libs.compositeAdapter))

    implementation(project(Config.Deps.Libs.decorations))
    implementation(project(Config.Deps.Libs.differentBindings))
    implementation(project(Config.Deps.Libs.innerRecyclerview))
    implementation(project(Config.Deps.Libs.stateAsCells))
}