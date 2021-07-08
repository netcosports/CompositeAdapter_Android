buildscript {
    repositories {
        google()
        gradlePluginPortal()
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(Config.Plugins.buildGradle)
        classpath(Config.Plugins.kotlinGradle)
    }
}

allprojects {
    repositories {
        google()
        gradlePluginPortal()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}