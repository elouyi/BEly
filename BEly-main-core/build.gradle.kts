plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version Versions.kotlin
}

dependencies {
    api(project(":BEly-main-api"))
    api("io.ktor:ktor-client-cio:${Versions.ktor}")
    api("io.ktor:ktor-client-json:${Versions.ktor}")
    api("io.ktor:ktor-client-gson:${Versions.ktor}")
    implementation("com.google.zxing:core:${Versions.zxing}")
    implementation("com.google.zxing:javase:${Versions.zxing}")

}
