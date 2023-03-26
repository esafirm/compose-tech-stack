package nolambda.techstack.app.appdetail.components.pager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import nolambda.techstack.app.appdetail.components.DetailItemView
import nolambda.techstack.app.appdetail.components.EmptyView
import kotlin.random.Random

@Composable
internal fun NativeLibrariesListView(
    nativeLibs: List<String>,
) {
    if (nativeLibs.isEmpty()) {
        EmptyView()
        return
    }
    val itemNumbers = remember { 200 }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
//        items(nativeLibs) { lib ->
//            DetailItemView(lib)
//        }
        items(itemNumbers) {
            DetailItemView("This is an item in $it")
        }
    }
}
