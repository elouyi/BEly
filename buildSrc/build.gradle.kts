plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("name.neuhalfen.projects.crypto.bouncycastle.openpgp:bouncy-gpg:2+")
}
