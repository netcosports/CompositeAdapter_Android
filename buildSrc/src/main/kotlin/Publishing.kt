import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.plugins.signing.SigningExtension

fun Project.publishing(
    configure: PublishingExtension.() -> Unit
): Unit = (this as ExtensionAware).extensions.configure("publishing", configure)

fun Project.signing(
    configure: Action<SigningExtension>
): Unit = (this as ExtensionAware).extensions.configure("signing", configure)

fun Project.initPublishing(
    artifactId: String,
    groupId: String,
    version: String,
    sources: String,
    sourcesJar: TaskProvider<Jar>/*,
    javadocJar: TaskProvider<Jar>*/
) {
    plugins.apply("signing")
    plugins.apply("maven-publish")

    publishing {
        signing {
            useInMemoryPgpKeys(gpgSigningKey, gpgSigningPassphrase)
            sign(publications)
        }

        repositories {
            maven {
                this.name = "sonatype"
                this.url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                this.credentials {
                    this.username = sonatypeUsername
                    this.password = sonatypePassword
                }
            }
        }
        publications {
            register("aar", MavenPublication::class.java) {
                this.artifactId = artifactId
                this.groupId = groupId
                this.version = version

                this.artifact(sources)
//                this.artifact(javadocJar.get())
                this.artifact(sourcesJar.get())

                this.pom {
                    this.name.set("CompositeAdapter")
                    this.description.set("RecyclerView Adapter that delegates all UI logic to a Cell<T> created by the ViewModel")
                    this.url.set("https://github.com/netcosports/CompositeAdapter_Android")
                    this.packaging = "aar"

                    this.organization {
                        this.name.set("ORIGINS Digital")
                        this.url.set("https://github.com/netcosports")
                    }

                    this.licenses {
                        this.license {
                            this.name.set("The Apache License, Version 2.0")
                            this.url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    this.developers {
                        this.developer {
                            this.id.set("Woffkaa")
                            this.name.set("Vladimir Garkovich")
                            this.email.set("vladimir.garkovich@gmail.com")
                            this.url.set("https://github.com/Woffkaa")
                            this.organization.set("ORIGINS Digital")
                            this.organizationUrl.set("https://github.com/netcosports")
                        }
                        this.developer {
                            this.id.set("zakharchanka")
                            this.name.set("Yaroslav Zakharchenko")
                            this.email.set("yaraslau.zakharchanka@origins-digital.com")
                            this.url.set("https://github.com/zakharchanka")
                            this.organization.set("ORIGINS Digital")
                            this.organizationUrl.set("https://github.com/netcosports")
                        }
                        this.developer {
                            this.id.set("alexanderbliznyuk")
                            this.name.set("Alexander Blizniuk")
                            this.email.set("bliznyuk.alexander@gmail.com")
                            this.url.set("https://github.com/alexanderbliznyuk")
                            this.organization.set("ORIGINS Digital")
                            this.organizationUrl.set("https://github.com/netcosports")
                        }
                    }

                    this.scm {
                        this.url.set("https://github.com/netcosports/CompositeAdapter_Android")
                        this.connection.set("scm:git:git://github.com/netcosports/CompositeAdapter_Android.git")
                        this.developerConnection.set("scm:git:git://github.com/netcosports/CompositeAdapter_Android.git")
                    }
                }
            }
        }
    }
}