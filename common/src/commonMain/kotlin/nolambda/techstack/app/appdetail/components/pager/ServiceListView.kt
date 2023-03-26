package nolambda.techstack.app.appdetail.components.pager

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import nolambda.techstack.app.appdetail.components.DetailItemView
import nolambda.techstack.app.appdetail.components.EmptyView

@Composable
internal fun ServiceListView(
    services: List<String>,
) {
    if (services.isEmpty()) {
        EmptyView()
        return
    }
    LazyColumn {
        items(services) { lib ->
            DetailItemView(lib)
        }
    }
}
