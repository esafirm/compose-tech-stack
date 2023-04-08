package nolambda.techstack.app.appdetail.components.pager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nolambda.techstack.app.appdetail.components.DetailItemView
import nolambda.techstack.app.appdetail.components.EmptyView

@Composable
internal fun SimplePagerListView(
    itemContent: List<String>,
) {
    if (itemContent.isEmpty()) {
        EmptyView()
        return
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemContent) { lib ->
            DetailItemView(lib)
        }
    }
}
