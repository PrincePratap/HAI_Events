package com.cody.haievents.android.common.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.SentimentVerySatisfied
import androidx.compose.material.icons.outlined.TheaterComedy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class EventCategory(
    val id: Int = 0,
    val label: String,
    val icon: ImageVector,
    val isSeeAll: Boolean = false
)

/* ---------- list ---------- */
@Composable
 fun EventCategoriesItems(
    modifier: Modifier = Modifier,
    onCategoryClick: (Int) -> Unit = {},
    onSeeAllClick: () -> Unit = {}
) {
    val categories = listOf(
        EventCategory(1,"Open Mic", Icons.Outlined.Mic),
        EventCategory(7,"Stand-Up\nComedy", Icons.Outlined.SentimentVerySatisfied),
        EventCategory(6,"Concerts", Icons.Outlined.LibraryMusic),
        EventCategory(2,"Theatre", Icons.Outlined.TheaterComedy),
        EventCategory(5,"See all", Icons.Filled.KeyboardArrowRight, isSeeAll = true)
    )

    Surface(color = MaterialTheme.colorScheme.background) {
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { item ->
                // Wrap in Row so CategoryItem can be used (it requires RowScope)
                Row(
                    modifier = Modifier
                        .width(96.dp) // consistent tile width; tweak as needed
                        .padding(vertical = 4.dp)
                        .clickable {
                            if (item.isSeeAll) onSeeAllClick() else onCategoryClick(item.id)
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CategoryItem(
                        label = item.label,
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.size(48.dp) // keep your design
                            )
                        }
                    )
                }
            }
        }
    }
}

/* ---------- item (unchanged design) ---------- */
@Composable
fun RowScope.CategoryItem(
    label: String,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon()
        Spacer(Modifier.height(8.dp))
        Text(
            text = label,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 20.sp
        )
    }
}

/* ---------- preview ---------- */
@Preview(showBackground = true, widthDp = 420)
@Composable
fun EventCategoriesScreenPreview() {
    EventCategoriesItems()
}
