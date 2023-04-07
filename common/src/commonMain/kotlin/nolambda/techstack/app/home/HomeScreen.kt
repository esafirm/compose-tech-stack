package nolambda.techstack.app.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import nolambda.techstack.app.appdetail.AppDetailScreen
import nolambda.techstack.app.home.components.AppListView
import nolambda.techstack.app.home.components.HomeToolbarView
import nolambda.techstack.app.home.components.LoadingView

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { HomeScreenModel() }

        val state by screenModel.state.collectAsState()

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            HomeToolbarView()

            if (state.isLoading) {
                LoadingView()
            } else {
                AppListView(contentItems = state.items) { appItem ->
                    navigator.push(AppDetailScreen(appItem))
                }
            }
        }
    }
}
