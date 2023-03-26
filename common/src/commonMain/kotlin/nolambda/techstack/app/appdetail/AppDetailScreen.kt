package nolambda.techstack.app.appdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import nolambda.techstack.app.home.AppItem
import nolambda.techstack.app.utils.rememberFloatProgressionAsAnimation

class AppDetailScreen(
    private val appItem: AppItem,
) : Screen {

    private val pagerContentMap = mapOf<DetailTabs, PagerContent<*>>(
        DetailTabs.NATIVE_LIBS to PagerContent(
            size = appItem.nativeLibs.length,
            initialItems = { appItem.nativeLibs.split("") },
            composable = { items ->
                NativeLibrariesListView(items.toList())
            }
        ),
        DetailTabs.SERVICES to PagerContent(
            size = appItem.nativeLibs.length,
            initialItems = { appItem.nativeLibs.split("") },
            composable = { items ->
                NativeLibrariesListView(items.toList())
            }
        ),
    )

    private val tabs by lazy { pagerContentMap.keys.map { it.caption } }

    class PagerContent<T : Any>(
        val size: Int,
        val initialItems: () -> Collection<T>,
        val composable: @Composable (items: Collection<T>) -> Unit,
    ) {
        private val itemsState by lazy(LazyThreadSafetyMode.NONE) {
            mutableStateOf(initialItems())
        }

        fun sort() {
            val (items, setItems) = itemsState
            setItems(items.reversed())
        }

        @Composable
        fun draw() {
            composable.invoke(itemsState.value)
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val verticalScrollState = rememberScrollState()
        val toolbarHeight = 56.dp

        val animatedAlpha = rememberFloatProgressionAsAnimation(
            value = verticalScrollState.value.toFloat(),
            max = 1F,
            min = 0F,
            progression = 0.1F
        )

        BoxWithConstraints {
            val screenHeight = maxHeight

            AppDetailToolbarView(
                modifier = Modifier.height(toolbarHeight)
            )

            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(verticalScrollState)
            ) {
                Column(
                    modifier = Modifier.padding(top = toolbarHeight)
                        .alpha(animatedAlpha)
                ) {
                    AppDetailItemView(appItem, Modifier.padding(horizontal = 16.dp))
                    Spacer(modifier = Modifier.requiredHeight(8.dp))
                    LibrariesIconsView(modifier = Modifier.padding(horizontal = 16.dp))
                }
                Spacer(modifier = Modifier.requiredHeight(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth().height(screenHeight - toolbarHeight)
                ) {

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

                    // Nested scrolling doesn't work on desktop :(
                    // https://github.com/JetBrains/compose-multiplatform/issues/653
                    val nestedScrollConnection = remember {
                        object : NestedScrollConnection {
                            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                                // Inverted because negative delta means scrolling down
                                val delta = -available.y

                                // Pass the scroll to parent
                                val consumed = verticalScrollState.dispatchRawDelta(delta)

                                // If scrolling down, we want to synchronize the scroll between parent and child
                                return if (delta > 0) {
                                    Offset(0f, -consumed)
                                } else {
                                    Offset.Zero
                                }
                            }
                        }
                    }

                    HorizontalPager(
                        count = tabs.size,
                        state = pagerState,
                        modifier = Modifier.fillMaxHeight().nestedScroll(nestedScrollConnection),
                    ) { page ->
                        val pagerTag = DetailTabs.values()[page]
                        val pagerInsideContent = pagerContentMap[pagerTag]!!
                        pagerInsideContent.draw()
                    }
                }
            }
        }
    }
}
