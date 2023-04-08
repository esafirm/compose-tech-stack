package nolambda.techstack.app.appdetail.components.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import nolambda.techstack.app.appdetail.components.DetailItemView
import nolambda.techstack.app.appdetail.components.EmptyView

internal class FilterableItem(
    val text: String,
    val identifier: String,
    val color: Color,
)

@Composable
internal fun FilterableListView(
    itemContent: List<FilterableItem>,
) {
    val (filtered, setFiltered) = remember { mutableStateOf(itemContent) }
    val (selected, setSelected) = remember { mutableStateOf<String?>(null) }

    val chips = remember {
        itemContent
            .distinctBy { it.identifier }
            .map { it.identifier to it.color }
    }

    if (itemContent.isEmpty()) {
        EmptyView()
        return
    }

    Column {
        FilterChips(
            filterChip = chips,
            selectedChip = selected
        ) { filter ->
            // Unselect filter
            if (filter == selected) {
                setSelected(null)
                setFiltered(itemContent)
            } else {
                setSelected(filter)
                setFiltered(itemContent.filter { it.identifier == filter })
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(filtered) { lib ->
                DetailItemView(
                    item = lib.identifier,
                    labelColor = lib.color
                )
            }
        }
    }
}

@Composable
private fun FilterChips(
    filterChip: List<Pair<String, Color>>,
    selectedChip: String?,
    onFilterClick: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        filterChip.forEach { (text, color) ->
            Row(
                modifier = Modifier
                    .clickable { onFilterClick(text) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .background(color, RoundedCornerShape(4.dp))
                        .width(16.dp)
                        .height(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                val isSelected = text == selectedChip

                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) MaterialTheme.colors.primary else Color.Black,
                )
            }
        }
    }
}
