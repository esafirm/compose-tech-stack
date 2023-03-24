package nolambda.techstack.common.components.plant

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nolambda.techstack.common.LocalImage
import nolambda.techstack.common.model.Plant

@Composable
internal fun PlantImage(currentItem: Plant, modifier: Modifier) {
    Crossfade(
        targetState = currentItem,
        modifier = modifier,
        animationSpec = tween(700)
    ) {
        LocalImage(imageResourceName = it.image!!,
              contentDescription = "",

        )
    }
}
