package nolambda.techstack.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import nolambda.techstack.app.home.HomeScreen

@Composable
internal fun App() {
    Navigator(HomeScreen())
}
