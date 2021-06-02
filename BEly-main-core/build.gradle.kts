plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":BEly-main-api"))
    api("io.ktor:ktor-client-cio:${Versions.ktor}")
    api("io.ktor:ktor-client-json:${Versions.ktor}")
    api("io.ktor:ktor-client-gson:${Versions.ktor}")
}
