package nolambda.techstack.app.appdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MaterialTabIndicator(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    backgroundColor: Color = Color.Transparent,
    indicatorColor: Color = MaterialTheme.colors.primary,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = backgroundColor,
        contentColor = Color.Transparent,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(currentTabPosition = tabPositions[selectedTabIndex])
                    .height(4.dp)
                    .background(indicatorColor, RoundedCornerShape(topEnd = 4.dp, topStart = 4.dp))
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = selectedTabIndex == index
            val textColor = if (isSelected) MaterialTheme.colors.primary else Color.Gray
            Tab(
                text = {
                    Text(
                        text = title,
                        color = textColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                selected = isSelected,
                onClick = { onTabSelected(index) }
            )
        }
    }
}
