package nolambda.techstack.app.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

private class Ref<T>(var value: T)

internal val PrintLogger = { message: String -> println(message) }

@Composable
fun Rebugger(
    trackMap: Map<String, Any?>,
    composableName: String,
    logger: (message: String) -> Unit = PrintLogger,
) {
    LaunchedEffect(Unit) {
        logger("🐞 Rebugger activated on `$composableName`")
    }

    val count = remember { Ref(0) }
    val flag = remember { Ref(false) }
    SideEffect {
        count.value++
    }

    val changeLog = StringBuilder()
    for ((key, newArg) in trackMap) {
        var recompositionTrigger by remember { mutableStateOf(false) }
        val oldArg = remember(recompositionTrigger) { newArg }

        if (oldArg != newArg) {
            changeLog.append("\n\t `$key` changed from `$oldArg` to `$newArg`, ")
            flag.value = true

            // This cause invalidation so remember will change its value
            recompositionTrigger = !recompositionTrigger
        }
    }

    if (changeLog.isNotEmpty()) {
        logger("🐞$composableName recomposed because $changeLog")
    } else {
        if (count.value >= 1 && !flag.value) {
            logger("🐞$composableName recomposed not because of param change")
        } else {
            flag.value = false
        }
    }
}
