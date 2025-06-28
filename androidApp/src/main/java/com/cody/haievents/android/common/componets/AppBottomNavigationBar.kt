package com.cody.haievents.android.common.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Data class to represent each item in the bottom navigation bar
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val isSelected: Boolean = false
)

/**
 * A composable that displays the app's bottom navigation bar.
 */
@Composable
fun AppBottomNavigationBar(modifier: Modifier = Modifier) {
    // State to keep track of the selected item's index. Initialized to 0 for "Home".
    var selectedItemIndex by remember { mutableStateOf(0) }

    // List of navigation items. Using standard icons that resemble the ones in the image.
    val navItems = listOf(
        BottomNavItem("Home", Icons.Default.LocalActivity), // A ticket icon for the custom "my" logo
        BottomNavItem("Movies", Icons.Default.Videocam),
        BottomNavItem("Live Events", Icons.Default.ConfirmationNumber),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    // Colors derived from the image
    val selectedColor = Color(0xFFD32F2F)
    val unselectedColor = Color.Gray

    Surface(
        // The shadow is very subtle in the original, so we use a low elevation.
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // The thin divider line at the top of the navigation bar
            Divider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp)

            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                navItems.forEachIndexed { index, item ->
                    val isSelected = selectedItemIndex == index

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedItemIndex = index },
                        label = {
                            Text(text = item.label)
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        // Customize colors to match the screenshot
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = selectedColor,
                            selectedTextColor = selectedColor,
                            unselectedIconColor = unselectedColor,
                            unselectedTextColor = unselectedColor,
                            // Hide the default indicator to match the original UI
                            indicatorColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun AppBottomNavigationBarPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AppBottomNavigationBar()
        }
    }
}