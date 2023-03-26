package nolambda.techstack.app.appdetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun LibrariesIconsView(
    modifier: Modifier = Modifier,
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(4) {
            Image(
                painter = rememberAsyncImagePainter("https://avatars.githubusercontent.com/u/153802?v=4"),
                contentDescription = null,
                modifier = Modifier.requiredSize(32.dp)
                    .background(Color.LightGray.copy(alpha = 0.5F), RoundedCornerShape(48.dp))
                    .padding(8.dp)
            )
        }
    }
}
