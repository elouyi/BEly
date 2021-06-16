import com.elouyi.buildsrc.*

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version Versions.kotlin
    `maven-publish`
    signing
}

dependencies {
    api(kotlin("stdlib"))
    api(ktorClientCore)
    api(coroutines)
    api(kotlinx("serialization-json",Versions.serialization))
    api(kotlin("reflect"))
}

tasks.getByName<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compileKotlin") {
    kotlinOptions.freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
}

mavenPublish("bely-api")