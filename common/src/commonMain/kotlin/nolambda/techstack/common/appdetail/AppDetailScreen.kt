package nolambda.techstack.common.appdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import nolambda.techstack.common.appdetail.components.AppDetailItemView
import nolambda.techstack.common.appdetail.components.AppDetailToolbarView
import nolambda.techstack.common.appdetail.components.LibrariesIconsView
import nolambda.techstack.common.home.AppItem
import nolambda.techstack.common.utils.rememberDpProgressionAsAnimation
import nolambda.techstack.common.utils.rememberFloatProgressionAsAnimation

class AppDetailScreen(
    private val appItem: AppItem,
) : Screen {

    companion object {
        private const val MAX_TOOLBAR_HEIGHT_IN_DP = 200
        private const val MIN_TOOLBAR_HEIGHT_IN_DP = 56
        private const val TOOLBAR_PROGRESSION_IN_DP = 20
    }

    @Composable
    override fun Content() {
        val lazyState = rememberLazyListState()

        val animatedHeight = rememberDpProgressionAsAnimation(
            value = lazyState.firstVisibleItemIndex,
            max = MAX_TOOLBAR_HEIGHT_IN_DP,
            min = MIN_TOOLBAR_HEIGHT_IN_DP,
            progression = TOOLBAR_PROGRESSION_IN_DP,
        )

        val animatedAlpha = rememberFloatProgressionAsAnimation(
            value = lazyState.firstVisibleItemIndex.toFloat(),
            max = 1F,
            min = 0F,
            progression = 0.2F,
        )

        val toolbarModifier = Modifier.fillMaxWidth()
        val isMaxValue = animatedHeight.value.toInt() == MAX_TOOLBAR_HEIGHT_IN_DP

        Scaffold(
            topBar = {
                Column(
                    modifier = if (isMaxValue) {
                        toolbarModifier.wrapContentHeight()
                    } else {
                        toolbarModifier.height(animatedHeight)
                    }
                ) {
                    AppDetailToolbarView()

                    Column(modifier = Modifier.alpha(animatedAlpha)) {
                        AppDetailItemView(appItem, Modifier.padding(horizontal = 16.dp))
                        Spacer(modifier = Modifier.requiredHeight(8.dp))
                        LibrariesIconsView(modifier = Modifier.padding(horizontal = 16.dp))
                    }

                    Spacer(modifier = Modifier.requiredHeight(16.dp))
                }
            },
            content = {
                LazyColumn(
                    state = lazyState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 0.dp),
                ) {
                    items(50) { index ->
                        Text("Item $index")
                    }
                }
            }
        )
    }
}
