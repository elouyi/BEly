group = "com.elouyi"
version = Versions.BEly

buildscript {

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {
    version = Versions.BEly
    repositories {
        mavenCentral()
    }
}
