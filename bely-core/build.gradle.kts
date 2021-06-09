import com.elouyi.buildsrc.*

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version Versions.kotlin
    `maven-publish`
    maven
    signing
}

dependencies {
    api(project(":bely-api"))
    api("io.ktor:ktor-client-cio:${Versions.ktor}")
    api(ktorClient("okhttp"))
    api("io.ktor:ktor-client-json:${Versions.ktor}")
    api("io.ktor:ktor-client-gson:${Versions.ktor}")

    implementation("com.google.zxing:core:${Versions.zxing}")
    implementation("com.google.zxing:javase:${Versions.zxing}")

}


mavenPublish("bely-core")

