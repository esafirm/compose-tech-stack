package nolambda.techstack.app.appdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import nolambda.techstack.app.appdetail.components.AppDetailItemView
import nolambda.techstack.app.appdetail.components.AppDetailToolbarView
import nolambda.techstack.app.appdetail.components.LibrariesIconsView
import nolambda.techstack.app.appdetail.components.MaterialTabIndicator
import nolambda.techstack.app.appdetail.components.NativeLibrariesListView
import nolambda.techstack.app.home.AppItem
import nolambda.techstack.app.utils.rememberDpProgressionAsAnimation
import nolambda.techstack.app.utils.rememberFloatProgressionAsAnimation

class AppDetailScreen(
    private val appItem: AppItem,
) : Screen {

    companion object {
        private const val MAX_TOOLBAR_HEIGHT_IN_DP = 200
        private const val MIN_TOOLBAR_HEIGHT_IN_DP = 56
        private const val TOOLBAR_PROGRESSION_IN_DP = 20
    }

    private val contentMap = mapOf<String, @Composable () -> Unit>(
        "Native Libraries" to { NativeLibrariesListView(appItem.nativeLibs.split(" ")) },
        "Services" to { NativeLibrariesListView(emptyList()) },
    )

    private val tabs by lazy { contentMap.keys.toList() }

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

        val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }

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
                Column {
                    MaterialTabIndicator(
                        tabs = contentMap.keys.toList(),
                        selectedTabIndex = selectedTab,
                        onTabSelected = setSelectedTab
                    )
                    // Content
                    contentMap[tabs[selectedTab]]?.invoke()
                }
            }
        )
    }
}
