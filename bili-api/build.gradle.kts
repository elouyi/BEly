import com.elouyi.buildsrc.*

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version Versions.kotlin
    `maven-publish`
    signing
}

kotlin {
    explicitApi()

    sourceSets {

        val main by getting {
            dependencies {

                // region kotlin
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))
                api(coroutines)
                api(kotlinx("io",Versions.kotlinxIO))
                // endregion

                // region serialization
                api(kotlinx("serialization-json",Versions.serialization))
                api(kotlinx("serialization-protobuf",Versions.serialization))
                // endregion

                // region ktor
                api(ktor("client-core"))
                api(ktor("client-cio"))
                api(ktor("client-serialization"))
                // endregion

            }
        }

        val test by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

tasks.register("ntest") {
    ntest()
}

mavenPublish("bili-api")
