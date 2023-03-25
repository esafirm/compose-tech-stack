package nolambda.techstack.common.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import nolambda.techstack.common.appdetail.AppDetailScreen
import nolambda.techstack.common.home.components.AppListView
import nolambda.techstack.common.home.components.ToolbarView

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            ToolbarView()
            AppListView { appItem ->
                navigator.push(AppDetailScreen(appItem))
            }
        }
    }
}
