package nolambda.techstack.common

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import nolambda.techstack.common.home.HomeScreen

@Composable
internal fun App() {
    Navigator(HomeScreen())
}
