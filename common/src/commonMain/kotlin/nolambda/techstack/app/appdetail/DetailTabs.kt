package nolambda.techstack.app.appdetail

enum class DetailTabs(
    val caption: String,
) {
    NATIVE_LIBS("Native Libraries"),
    SERVICES("Services"),
    ACTIVITIES("Activities"),
    RECEIVERS("Broadcast Receivers"),
    PROVIDERS("Content Providers"),
    PERMISSIONS("Permissions"),
    META_DATA("Meta Data"),
    DEX("Dex"),
    SIGNATURE("Signature");
}

