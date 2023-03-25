package nolambda.techstack.common.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun AppRowItemView(
    appName: String,
    appId: String,
    appIcon: String,
    versionCode: String,
    versionName: String,
    architecture: String,
    nativeLibs: String,
    targetSdk: String,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        val painter = rememberAsyncImagePainter(appIcon)

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.requiredSize(48.dp)
                .background(Color.LightGray.copy(alpha = 0.5F), RoundedCornerShape(48.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = appName,
                style = MaterialTheme.typography.subtitle1,
            )
            Text(
                text = appId,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "$versionName ($versionCode)",
                style = MaterialTheme.typography.caption,
            )
            Row(
                modifier = Modifier.padding(top = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = architecture,
                    fontSize = 8.sp,
                    color = Color.White,
                    modifier = Modifier.background(Color.Black, RoundedCornerShape(8.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                )
                Text(
                    text = nativeLibs,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    modifier = Modifier.wrapContentWidth()
                )
                Text(
                    text = "Target: $targetSdk",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                )
            }
        }
    }
}
