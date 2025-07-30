package com.cody.haievents.android.common.componets



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A container for displaying different event categories.
 */
@Composable
fun EventCategoriesItems() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {


            // Row containing the four category items
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top
            ) {
                // Open Mic
                CategoryItem(
                    label = "Open Mic",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Mic,
                            contentDescription = "Open Mic",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                )

                // Stand-Up Comedy
                CategoryItem(
                    label = "Stand-Up\nComedy",
                    icon = {
                        // Custom composite icon to approximate the original
                        Icon(
                            imageVector = Icons.Outlined.SentimentVerySatisfied,
                            contentDescription = null, // Description is handled by the parent
                            modifier = Modifier.size(40.dp)
                        )
                    }
                )

                // Concerts
                CategoryItem(
                    label = "Concerts",
                    icon = {
                        Icon(
                            // Using LibraryMusic as a proxy for a violin/concert icon
                            imageVector = Icons.Outlined.LibraryMusic,
                            contentDescription = "Concerts",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                )

                // Theatre
                CategoryItem(
                    label = "Theatre",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.TheaterComedy,
                            contentDescription = "Theatre",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                )
            }
        }
    }
}

/**
 * A reusable composable for a single category item, consisting of an icon and a label.
 * This composable should be used within a Row and given a weight.
 */
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
            lineHeight = 20.sp // Improves spacing for multi-line text
        )
    }
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun EventCategoriesScreenPreview() {

        EventCategoriesItems()

}