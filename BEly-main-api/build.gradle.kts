import com.elouyi.buildsrc.*

plugins {
    kotlin("jvm")
}

dependencies {
    api(kotlin("stdlib"))
    api(ktorClientCore)
    api(coroutines)
}
