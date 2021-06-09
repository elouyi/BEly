package com.elouyi.buildsrc

import com.elouyi.buildsrc.sign.decryptBase64
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*


val org.gradle.api.Project.`sourceSets`: org.gradle.api.tasks.SourceSetContainer get() =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("sourceSets") as org.gradle.api.tasks.SourceSetContainer

/**
 * Configures the [sourceSets][org.gradle.api.tasks.SourceSetContainer] extension.
 */
fun org.gradle.api.Project.`sourceSets`(configure: Action<org.gradle.api.tasks.SourceSetContainer>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("sourceSets", configure)
fun org.gradle.api.Project.`publishing`(configure: Action<PublishingExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("publishing", configure)

val org.gradle.api.Project.`signing`: org.gradle.plugins.signing.SigningExtension get() =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("signing") as org.gradle.plugins.signing.SigningExtension

/**
 * Configures the [signing][org.gradle.plugins.signing.SigningExtension] extension.
 */
fun org.gradle.api.Project.`signing`(configure: Action<org.gradle.plugins.signing.SigningExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("signing", configure)


val org.gradle.api.Project.`publishing`: org.gradle.api.publish.PublishingExtension get() =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("publishing") as org.gradle.api.publish.PublishingExtension



fun Project.mavenPublish(aid: String) {
    afterEvaluate {
        val uname = System.getenv("MAVEN_USERNAME")
        val pwd = System.getenv("MAVEN_PASSWORD")
        val base64 = decryptBase64(System.getenv("KEYRINGBASE64"))
        val pp = System.getenv("PP")
        val keyId = System.getenv("KEY_ID")
        publishing {

            val sourcesJar by tasks.registering(Jar::class) {
                archiveClassifier.set("sources")
                from(sourceSets["main"].allSource)
            }

            val doc = tasks.register("javadocJar", Jar::class) {
                archiveClassifier.set("javadoc")
            }

            publications {
                create<MavenPublication>("mavenJava") {

                    groupId = "com.elouyi"
                    artifactId = aid
                    version = Versions.BEly
                    from(components["kotlin"])

                    pom {
                        scm {
                            url.set("https://github.com/elouyi/BEly")
                            connection.set("scm:https://github.com/elouyi/BEly.git")
                            developerConnection.set("scm:git://github.com/elouyi/BEly.git")
                        }

                        licenses {
                            license {
                                name.set("GNU Affero General Public License v3.0")
                                url.set("https://github.com/elouyi/BEly/blob/dev/LICENSE")
                            }
                        }
                        developers {
                            developer {
                                id.set("kazusa1412")
                                name.set("Elouyi")
                                email.set("kazusa1412@foxmail.com")
                            }
                        }
                    }

                    pom.withXml {
                        asNode().apply {
                            appendNode("description",project.description)
                            appendNode("name",aid)
                            appendNode("url","https://github.com/elouyi/BEly")
                        }
                    }

                    artifact(sourcesJar.get())
                    artifact(doc.get())
                }
            }



            repositories {
                maven {
                    credentials {

                        name = "OSSRH"
                        setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                        username = uname
                        password = pp
                    }

                }
            }
        }
        project.setProperty("signing.keyId",keyId)
        project.setProperty("signing.password",pp)
        project.setProperty("signing.secretKeyRingFile",base64)
        project.setProperty("ossrhUsername",uname)
        project.setProperty("ossrhPassword",pwd)
        signing {
            sign(publishing.publications["mavenJava"])
        }
    }


}