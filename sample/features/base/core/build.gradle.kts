plugins {
    kotlin("jvm")
    `java-library`
}

java.sourceCompatibility = Config.Build.javaVersion
java.targetCompatibility = Config.Build.javaVersion

project.tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
    kotlinOptions {
        jvmTarget = Config.Build.javaVersion.toString()
    }
}

dependencies {
    api(Config.Deps.Kotlin.kotlinJdk8)
    api(Config.Deps.Coroutines.core)
    api(Config.Deps.Koin.core)
}