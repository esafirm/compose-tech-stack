package nolambda.techstack.common.components.plant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nolambda.techstack.common.components.CircularProgress
import nolambda.techstack.common.model.Plant

@Composable
internal fun PlantProperty(currentItem: Plant, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(19.dp)
    ) {
        repeat(currentItem.properties.size) {
            CircularProgress(currentItem.properties[it])
        }
    }
}

