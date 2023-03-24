package nolambda.techstack.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import platform.UIKit.UIImage

@Composable
internal actual fun __LocalImage(imageResourceName: String, modifier: Modifier, contentDescription: String?) {
    val painter = remember { UIImage.imageNamed(imageResourceName)?.toSkiaImage()?.toComposeImageBitmap()?.let(::BitmapPainter) }
    if (painter != null) {
        androidx.compose.foundation.Image(
            modifier = modifier,
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.FillWidth,
        )
    } else {
        Row(
            modifier = modifier.background(MaterialTheme.colors.primary, RoundedCornerShape(6.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = contentDescription,
                modifier = Modifier.padding(16.dp),
                tint = Color.White
            )
            Text("Image not supported", modifier = Modifier.padding(16.dp), color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
