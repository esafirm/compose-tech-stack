package nolambda.techstack.common.home

data class AppItem(
    val appName: String,
    val appId: String,
    val appIcon: String,
    val versionName: String,
    val versionCode: String,
    val architecture: String,
    val nativeLibs: String,
    val targetSdk: String,
)
