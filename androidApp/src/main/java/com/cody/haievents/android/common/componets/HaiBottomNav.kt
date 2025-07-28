package com.cody.haievents.android.common.componets


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
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

/**
 * A data class to represent each item in our bottom navigation bar.
 */

/**
 * This is the main screen composable that includes the Scaffold and the bottom navigation.
 */
@Composable
fun HaiBottomNav() {
    // List of navigation items.
    // The "Home" item uses a standard icon as a placeholder for the custom logo.
    val navItems = listOf(
        BottomNavItem(label = "Home", icon = Icons.Filled.Home),
        BottomNavItem(label = "Blogs", icon = Icons.Filled.Groups),
        BottomNavItem(label = "My Tickets", icon = Icons.Filled.ConfirmationNumber),
        BottomNavItem(label = "Profile", icon = Icons.Filled.Person)
    )

    // State to keep track of the selected item's index. We start with "Home" selected.
    var selectedItemIndex by remember { mutableStateOf(0) }

    Scaffold(
        // The light gray background color for the screen content area, similar to the image.
        containerColor = Color(0xFFF8F9FA),
        bottomBar = {
            NavigationBar(
                // The navigation bar itself has a white background.
                containerColor = Color.White,
                // Add a subtle shadow/elevation to separate it from the content.
                tonalElevation = 8.dp
            ) {
                // Define colors based on the provided image.
                val selectedColor = Color(0xFFC9A227) // Gold color for selected item
                val unselectedColor = Color(0xFF5A5A5A) // Dark gray for unselected items

                // Create a NavigationBarItem for each item in our list.
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index },
                        label = { Text(item.label) },
                        icon = {
                            // NOTE: In a real app, the "Home" icon would be a custom drawable.
                            // You would use painterResource to load your logo, for example:
                            // Image(painter = painterResource(id = R.drawable.hai_events_logo), ...)
                            Icon(imageVector = item.icon, contentDescription = item.label)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = selectedColor,
                            selectedTextColor = selectedColor,
                            unselectedIconColor = unselectedColor,
                            unselectedTextColor = unselectedColor,
                            // The indicator is the pill-shaped background. The reference
                            // image does not have one, so we make it transparent.
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        // This is where your screen's content would go.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Content for: ${navItems[selectedItemIndex].label}")
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF8F9FA)
@Composable
fun BottomNavPreview() {

    HaiBottomNav()

}