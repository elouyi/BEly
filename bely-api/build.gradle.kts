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


mavenPublish("bely-api")