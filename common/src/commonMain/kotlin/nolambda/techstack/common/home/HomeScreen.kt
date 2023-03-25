package nolambda.techstack.common.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import nolambda.techstack.common.home.components.AppListView
import nolambda.techstack.common.home.components.ToolbarView

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            ToolbarView()
            AppListView {

            }
        }
    }
}
