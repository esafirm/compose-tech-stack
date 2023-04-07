package nolambda.techstack.app.appdetail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
internal fun AppDetailToolbarView(
    modifier: Modifier = Modifier,
) {
    val navigator = LocalNavigator.currentOrThrow

    TopAppBar(
        modifier = modifier,
        backgroundColor = androidx.compose.ui.graphics.Color.White,
        elevation = 0.dp,
        title = { Box(Modifier) },
        navigationIcon = {
            IconButton(
                onClick = navigator::pop
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
    )
}
