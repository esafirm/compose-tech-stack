package nolambda.techstack.app.home.components

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun HomeToolbarView() {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp,
        title = {
            Text(
                text = "TechStack",
                color = Color.Black,
                style = MaterialTheme.typography.h6,
            )
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.requiredSize(24.dp)
                )
            }
        }
    )
}
