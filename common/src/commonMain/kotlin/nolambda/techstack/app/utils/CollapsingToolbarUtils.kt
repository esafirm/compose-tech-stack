package nolambda.techstack.app.utils

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

/**
 * This function will return a [Dp] value that will be animated based on the value and progression.
 */
@Composable
fun rememberDpProgressionAsAnimation(
    value: Int,
    max: Int,
    min: Int,
    progression: Int,
): Dp {
    val toolbarHeight = remember(value) {
        val progress = value * progression
        max(min, max - progress)
    }
    val animatedValue by animateDpAsState(targetValue = toolbarHeight.dp)

    return animatedValue
}

/**
 * This function will return a [Float] value that will be animated based on the value and progression.
 */
@Composable
fun rememberFloatProgressionAsAnimation(
    value: Float,
    max: Float,
    min: Float,
    progression: Float,
): Float {
    val toolbarHeight = remember(value) {
        val progress = value * progression
        max(min, max - progress)
    }
    val animatedValue by animateFloatAsState(targetValue = toolbarHeight.toFloat())

    return animatedValue
}
