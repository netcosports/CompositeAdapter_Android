import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar

fun Project.publishing(
    configure: PublishingExtension.() -> Unit
): Unit = (this as ExtensionAware).extensions.configure("publishing", configure)

fun Project.initPublishing(artifactId: String, groupId: String, version: String, sources: String, sourcesJar: TaskProvider<Jar>) {
    plugins.apply("maven-publish")

    publishing {
        publications {
            register("aar", MavenPublication::class.java) {
                this.artifactId = artifactId
                this.groupId = groupId
                this.version = version

                this.artifact(sources)
                this.artifact(sourcesJar.get())
                this.pom {
                    this.name.set("CompositeAdapter")
                    this.description.set("RecyclerView Adapter that delegates all UI logic to a Cell<T> created by the ViewModel")
                    this.url.set("https://github.com/netcosports/CompositeAdapter_Android")
                }
            }
        }
    }
}