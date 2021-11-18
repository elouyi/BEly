group = "com.elouyi"
version = Versions.BEly

buildscript {

    repositories {
        maven("https://maven.aliyun.com/nexus/content/groups/public/")
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {
    version = Versions.BEly
    group = "com.elouyi"
    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/nexus/content/groups/public/")
        mavenCentral()
    }

}
