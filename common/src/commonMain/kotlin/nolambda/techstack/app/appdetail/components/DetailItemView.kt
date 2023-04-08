package nolambda.techstack.app.appdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun DetailItemView(
    item: String,
) {
    Text(
        text = item,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
fun DetailItemView(
    firstRow: String,
    secondRow: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = firstRow,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = secondRow,
            style = MaterialTheme.typography.caption,
        )
        Divider(modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
    }
}

@Composable
internal fun DetailItemView(
    item: String,
    labelColor: Color,
) {
    Box {
        Row(
            modifier = Modifier.height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier.width(8.dp)
                    .fillMaxHeight()
                    .background(labelColor, shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
            )

            Text(
                text = item,
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Divider(modifier = Modifier.fillMaxWidth())
    }
}

