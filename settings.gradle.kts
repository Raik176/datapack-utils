pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev")
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.kikugie.dev/snapshots")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.6-beta.1"
}

stonecutter {
    centralScript = "build.gradle.kts"
    kotlinController = true
    create(rootProject) {
        versions("1.19", "1.20.1", "1.20.6", "1.21.1", "1.21.3")
        vcsVersion = "1.21.1"
        branch("fabric")
        branch("forge")
        branch("neoforge") { versions("1.20.6", "1.21.1", "1.21.3") }
    }
}

rootProject.name = "Datapack Utils"