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
}

val androidSourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}
/*val emptyJavadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}*/

initPublishing(
    artifactId = Config.Publishing.compositeAdapter,
    groupId = Config.Publishing.compositeAdapterGroupId,
    version = Config.Publishing.compositeAdapterVersion,
    sources = "$buildDir/outputs/aar/${project.name}-release.aar",
    sourcesJar = androidSourcesJar/*,
    javadocJar = emptyJavadocJar*/
)