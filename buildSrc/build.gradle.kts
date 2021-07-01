plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    jcenter()
    google()
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "https://artifactory-blr.netcodev.com/artifactory/libs-release") {
        credentials {
            username = properties["repoUsername"].toString()
            password = properties["repoPassword"].toString()
        }
    }
}

dependencies {
    implementation("org.jfrog.buildinfo:build-info-extractor-gradle:4.24.4")
}