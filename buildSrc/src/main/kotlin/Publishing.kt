import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

fun Project.publishing(
    configure: PublishingExtension.() -> Unit
): Unit = (this as ExtensionAware).extensions.configure("publishing", configure)

fun Project.initPublishing(artifactId: String, groupId: String, version: String) {
    plugins.apply("maven-publish")

    publishing {
        publications.forEach { targetPublication ->
            if (targetPublication is MavenPublication) {
                targetPublication.artifactId = targetPublication.artifactId.replace(project.name, artifactId)
                targetPublication.groupId = groupId
                targetPublication.version = version
            }
        }
    }
}