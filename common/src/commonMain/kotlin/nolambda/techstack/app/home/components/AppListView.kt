package nolambda.techstack.app.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import nolambda.techstack.app.home.AppItem

@Composable
internal fun AppListView(
    onItemClick: (AppItem) -> Unit,
) {
    val contentItems = listOf(
        AppItem(
            appName = "App Name",
            appId = "com.nolambda.app",
            appIcon = "https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/04/attachment_82290822-e1492536097660.png?auto=format&q=60&fit=max&w=930",
            versionName = "1.0.0",
            versionCode = "1",
            architecture = "x86",
            nativeLibs = "lib1.so, lib2.so",
            targetSdk = "29",
        ),
        AppItem(
            appName = "Tiket",
            appId = "com.nolambda.app",
            appIcon = "https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/04/attachment_82290822-e1492536097660.png?auto=format&q=60&fit=max&w=930",
            versionName = "1.0.0",
            versionCode = "1",
            architecture = "32 Bit",
            nativeLibs = "lib1.so, lib2.so",
            targetSdk = "33",
        ),
        AppItem(
            appName = "Tiket",
            appId = "com.nolambda.app",
            appIcon = "https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/04/attachment_82290822-e1492536097660.png?auto=format&q=60&fit=max&w=930",
            versionName = "1.0.0",
            versionCode = "1",
            architecture = "32 Bit",
            nativeLibs = "lib1.so, lib2.so",
            targetSdk = "33",
        ),
    )

    LazyColumn {
        items(contentItems) { item ->
            AppRowItemView(
                appName = item.appName,
                appId = item.appId,
                appIcon = item.appIcon,
                versionName = item.versionName,
                versionCode = item.versionCode,
                architecture = item.architecture,
                nativeLibs = item.nativeLibs,
                targetSdk = item.targetSdk,
            ) {
                onItemClick(item)
            }
        }
    }
}
