import groovy.lang.GroovyObject
import org.gradle.api.Project
import org.gradle.api.internal.HasConvention
import org.gradle.kotlin.dsl.delegateClosureOf
import org.gradle.kotlin.dsl.getPluginByName
import org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention
import org.jfrog.gradle.plugin.artifactory.dsl.DoubleDelegateWrapper
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig

val Project.Artifactory: ArtifactoryPluginConvention
    get() = ((this as? Project)?.convention
        ?: (this as HasConvention).convention).getPluginByName("artifactory")

fun Project.Artifactory(
    configure: ArtifactoryPluginConvention.() -> Unit
): Unit = configure(Artifactory)

fun Project.initArtifactory(contextUrl: String, publications: Array<String>) {
    plugins.apply("com.jfrog.artifactory")

    Artifactory {
        setContextUrl(contextUrl)
        publish(
            delegateClosureOf<PublisherConfig> {
                repository(
                    delegateClosureOf<DoubleDelegateWrapper> {
                        setProperty("repoKey", "libs-release-local")
                        setProperty("username", repoUsername)
                        setProperty("password", repoPassword)
                    }
                )
                defaults(
                    delegateClosureOf<GroovyObject> {
                        invokeMethod("publications", publications)
                    }
                )
            }
        )
    }
}