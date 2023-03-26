package nolambda.techstack.app.appdetail.components.pager

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import nolambda.techstack.app.appdetail.components.DetailItemView
import nolambda.techstack.app.appdetail.components.EmptyView
import kotlin.random.Random

@Composable
internal fun NativeLibrariesListView(
    listState: LazyListState = rememberLazyListState(),
    nativeLibs: List<String>,
) {
    if (nativeLibs.isEmpty()) {
        EmptyView()
        return
    }
    LazyColumn(
        state = listState,
    ) {
//        items(nativeLibs) { lib ->
//            DetailItemView(lib)
//        }
        items(Random.nextInt(150)) {
            DetailItemView("This is an item in $it")
        }
    }
}
