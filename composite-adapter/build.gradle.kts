plugins {
    id(Config.Plugins.androidLibrary)
    kotlin(Config.Plugins.android)
}

android {
    compileSdkVersion(Config.Build.compileSdk)
    buildToolsVersion(Config.Build.buildTools)

    defaultConfig {
        minSdkVersion(Config.Build.minSdk)
        targetSdkVersion(Config.Build.targetSdk)
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
}

dependencies {
    compileOnly(Config.Deps.Kotlin.kotlinJdk8)

    implementation(Config.Deps.AndroidX.appcompat)
    compileOnly(Config.Deps.AndroidX.recycler)
    compileOnly(Config.Deps.AndroidX.swipeRefresh)
}

initPublishing(
    artifactId = Config.Publishing.compositeAdapter,
    groupId = Config.Publishing.compositeAdapterGroupId,
    version = Config.Publishing.compositeAdapterVersion
)
initArtifactory(
    contextUrl = Config.Publishing.contextUrl,
    publications = Config.Publishing.jvm() + Config.Publishing.kmm()
)