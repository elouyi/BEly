import com.elouyi.buildsrc.*

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version Versions.kotlin
    `maven-publish`
    signing
}

dependencies {
    api(project(":bely-api"))
    api("io.ktor:ktor-client-cio:${Versions.ktor}")
    api(ktorClient("okhttp"))
    api("io.ktor:ktor-client-json:${Versions.ktor}")
    api("io.ktor:ktor-client-gson:${Versions.ktor}")
    api(ktorClient("websockets"))
    implementation("com.google.zxing:core:${Versions.zxing}")
    implementation("com.google.zxing:javase:${Versions.zxing}")

    implementation("org.java-websocket","Java-WebSocket",Versions.javaWebsocket)
}

tasks.getByName<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compileKotlin") {
    kotlinOptions.freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
}

mavenPublish("bely-core")

