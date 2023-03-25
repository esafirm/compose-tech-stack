package nolambda.techstack.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import nolambda.techstack.common.components.plant.PlantImage
import nolambda.techstack.common.components.plant.PlantProperty
import nolambda.techstack.common.model.Plant


@Composable
@OptIn(ExperimentalPagerApi::class)
internal fun ItemPager(
    pagerState: PagerState,
    pages: List<Plant>
) {
    var currentItem by remember {
        mutableStateOf(Plant(image ="plant1.png"))
    }

    LaunchedEffect(pagerState) {
        // get current page
        snapshotFlow { pagerState.currentPage }.collect { page ->
            currentItem = pages[page]
        }
    }
    HorizontalPager(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        count = pages.size,
        state = pagerState,
    ) { page ->
        Box(
            Modifier
                .padding(top = 50.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            PlantImage(
                currentItem,
                Modifier
                    .padding(bottom = 80.dp, end = 100.dp)
                    .align(Alignment.CenterStart)
            )
            PlantProperty(
                currentItem, Modifier
                    .padding(
                        end = 26.dp,
                        bottom = 50.dp
                    )
                    .align(Alignment.CenterEnd)
                    .wrapContentWidth()
            )
        }
    }
}
