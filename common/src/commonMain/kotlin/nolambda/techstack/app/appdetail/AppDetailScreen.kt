package nolambda.techstack.app.appdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import nolambda.techstack.app.appdetail.components.AppDetailItemView
import nolambda.techstack.app.appdetail.components.AppDetailToolbarView
import nolambda.techstack.app.appdetail.components.ItemsSortingView
import nolambda.techstack.app.appdetail.components.LibrariesIconsView
import nolambda.techstack.app.appdetail.components.MaterialTabIndicator
import nolambda.techstack.app.appdetail.components.pager.NativeLibrariesListView
import nolambda.techstack.app.appdetail.components.pager.ServiceListView
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

    private val pagerContentMap = mapOf<DetailTabs, PagerContent<*>>(
        DetailTabs.NATIVE_LIBS to PagerContent(
            size = appItem.nativeLibs.length,
            initialItems = { appItem.nativeLibs.split("") },
            composable = { items, listState ->
                NativeLibrariesListView(listState, items.toList())
            }
        ),
        DetailTabs.SERVICES to PagerContent(
            size = appItem.nativeLibs.length,
            initialItems = { appItem.nativeLibs.split("") },
            composable = { items, listState ->
                NativeLibrariesListView(listState, items.toList())
            }
        ),
    )

    private val tabs by lazy { pagerContentMap.keys.map { it.caption } }

    class PagerContent<T : Any>(
        val size: Int,
        val initialItems: () -> Collection<T>,
        val composable: @Composable (items: Collection<T>, listState: LazyListState) -> Unit,
    ) {
        private val itemsState by lazy(LazyThreadSafetyMode.NONE) {
            mutableStateOf(initialItems())
        }

        fun sort() {
            val (items, setItems) = itemsState
            setItems(items.reversed())
        }

        @Composable
        fun draw(listState: LazyListState) {
            composable.invoke(itemsState.value, listState)
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    override fun Content() {
        val scrollState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        val animatedHeight = rememberDpProgressionAsAnimation(
            value = scrollState.firstVisibleItemIndex,
            max = MAX_TOOLBAR_HEIGHT_IN_DP,
            min = MIN_TOOLBAR_HEIGHT_IN_DP,
            progression = TOOLBAR_PROGRESSION_IN_DP,
        )

        val animatedAlpha = rememberFloatProgressionAsAnimation(
            value = scrollState.firstVisibleItemIndex.toFloat(),
            max = 1F,
            min = 0F,
            progression = 0.2F,
        )

        val toolbarModifier = Modifier.fillMaxWidth()
        val isMaxValue = animatedHeight.value.toInt() == MAX_TOOLBAR_HEIGHT_IN_DP

        Column {
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

            val pagerState = rememberPagerState()

            MaterialTabIndicator(
                tabs = tabs,
                selectedTabIndex = pagerState.currentPage,
                onTabSelected = {
                    scope.launch { pagerState.animateScrollToPage(it) }
                }
            )

            val tab = DetailTabs.values()[pagerState.currentPage]
            val pagerContent = pagerContentMap[tab]!!

            ItemsSortingView(
                itemCounts = pagerContent.size,
                onSort = pagerContent::sort
            )

            HorizontalPager(
                count = tabs.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                val pagerTag = DetailTabs.values()[page]
                val pagerInsideContent = pagerContentMap[pagerTag]!!
                pagerInsideContent.draw(scrollState)
            }
        }
    }
}
