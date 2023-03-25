package nolambda.techstack.common.components.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import nolambda.techstack.common.model.Plant
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun TabItem(
    pagerState: PagerState,
    index: Int,
    plant: Plant,
) {
    val scope = rememberCoroutineScope()
    val isSelected = pagerState.currentPage == index
    val color: Color by animateColorAsState(
        if (isSelected) Color.Black else Gray
    )
    Tab(
        modifier = Modifier.zIndex(6f)
            .clip(RoundedCornerShape(50)),
        text = {
            Text(
                text = plant.name ?: "", color = color,
            )
        },
        selected = isSelected,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index, pagerState.currentPageOffset)
            }
        },
    )
}

