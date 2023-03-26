package nolambda.techstack.app.appdetail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun NativeLibrariesListView(
    nativeLibs: List<String>,
) {
    if (nativeLibs.isEmpty()) {
        EmptyView()
        return
    }

    val (sortedItems, setSortedItems) = remember { mutableStateOf(nativeLibs.sorted()) }

    LazyColumn {
        stickyHeader {
            ItemsSortingView(
                itemCounts = nativeLibs.size,
                onSort = {
                    setSortedItems(sortedItems.reversed())
                }
            )
        }
        items(sortedItems) { lib ->
            DetailItemView(lib)
        }
    }
}
