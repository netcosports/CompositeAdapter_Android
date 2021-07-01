import org.gradle.api.Project

const val MASTER_BRANCH = "master"

val Project.repoUsername: String get() = properties["repoUsername"].toString()
val Project.repoPassword: String get() = properties["repoPassword"].toString()

fun getBranchName(): String {
    return System.getenv("BRANCH_NAME") ?: MASTER_BRANCH
}

fun getCustomVersionName(): String {
    return System.getenv("VERSION_NAME") ?: Config.Build.versionName
}

fun getCustomVersionCode(): Int {
    val versionCode = System.getenv("VERSION_CODE")?.toIntOrNull()
    if (versionCode != null) {
        return versionCode
    }
    val buildNumber = System.getenv("BUILD_NUMBER")?.toIntOrNull()
    if (buildNumber != null) {
        return buildNumber + Config.Build.versionOffset
    }
    return 1
}

fun getDefaultAssetsPath(flavor: String, name: String): String {
    return "src/$flavor/assets/$name"
}