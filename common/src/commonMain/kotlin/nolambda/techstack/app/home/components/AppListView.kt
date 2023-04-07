package nolambda.techstack.app.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import nolambda.techstack.app.home.AppItem

@Composable
internal fun AppListView(
    contentItems: List<AppItem>,
    onItemClick: (AppItem) -> Unit,
) {
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
