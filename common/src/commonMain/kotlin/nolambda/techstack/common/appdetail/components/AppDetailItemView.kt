package nolambda.techstack.common.appdetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberAsyncImagePainter
import nolambda.techstack.common.home.AppItem

@Composable
internal fun AppDetailItemView(
    appItem: AppItem,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.composed { fillMaxWidth() }
    ) {
        val painter = rememberAsyncImagePainter(appItem.appIcon)

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.requiredSize(72.dp)
                .clip(RoundedCornerShape(72.dp))
                .background(Color.LightGray.copy(alpha = 0.5F), RoundedCornerShape(48.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = appItem.appName,
                style = MaterialTheme.typography.subtitle1,
            )
            Text(
                text = appItem.appId,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "${appItem.versionName} (${appItem.versionCode})",
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = "Target: ${appItem.targetSdk}",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
            )
        }
    }
}
