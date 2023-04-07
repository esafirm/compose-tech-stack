package nolambda.techstack.app.data

data class AppData(
    val appName: String,
    val appId: String,
    val appIcon: String,
    val version: AppVersion,
    val buildInfo: BuildInfo,
    val detailData: AppDetailData,
)

data class AppVersion(
    val versionName: String,
    val versionCode: String,
)

data class BuildInfo(
    val architecture: String,
    val targetSdk: String,
    val compileSdk: String,
    val minSdk: String,
)

data class AppDetailData(
    val nativeLibs: List<NativeLib>,
    val permissions: List<String>,
    val activities: List<String>,
    val services: List<String>,
    val receivers: List<String>,
    val providers: List<String>,
    val dexes: List<String>,
    val metaData: List<Metadata>,
)

data class NativeLib(
    val name: String,
    val size: String,
)

data class Metadata(
    val key: String,
    val value: String,
)
