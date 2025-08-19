package com.cody.haievents.android.common.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController

import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.cody.haievents.android.R
import com.cody.haievents.android.common.theming.AlmostBlack
import com.cody.haievents.android.common.theming.GoldenYellow
import com.cody.haievents.android.screens.destinations.BlogsDestination
import com.cody.haievents.android.screens.destinations.HomePageDestination
import com.cody.haievents.android.screens.destinations.MyTicketsDestination
import com.cody.haievents.android.screens.destinations.ProfileDestination


data class NavItem(
    val label: String,
    val selectedImageResId: Int,
    val unselectedImageResId: Int,
    val route: String
)

val navItems = listOf(
    NavItem("Home", R.drawable.ic_icon_hai , R.drawable.ic_icon_hai, HomePageDestination.route),
    NavItem("Blogs",R.drawable.ic_icon_se_blog, R.drawable.ic_un_blogs, BlogsDestination.route),
    NavItem("My Tickets",R.drawable.ic_icon_se_my_ticket,R.drawable.ic_icon_un_mytickets, MyTicketsDestination.route),
    NavItem("Profile", R.drawable.ic_icon_se_profile,R.drawable.ic_icon_un_profile, ProfileDestination.route), // Use brown color and bold text styling in UI
)

val bottomNavRoutes = listOf(
    HomePageDestination.route,
    BlogsDestination.route,
    MyTicketsDestination.route,
    ProfileDestination.route,
)
@Composable
fun CustomBottomNavBar(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit,
    navHostController: NavHostController
) {
    val currentDestination = navHostController.currentDestinationAsState().value
    val currentRoute = currentDestination?.route
    if (currentRoute !in bottomNavRoutes) return

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                NavigationItem(
                    item = item,
                    isSelected = item == selectedItem,
                    onClick = { onItemSelected(item) }
                )
            }
        }
    }
}

@Composable
fun NavigationItem(item: NavItem, isSelected: Boolean, onClick: () -> Unit) {


    val contentColor = if (isSelected) GoldenYellow else  AlmostBlack
    val icon = if (isSelected) item.selectedImageResId else item.unselectedImageResId

    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = item.label,
            tint = contentColor,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.label,
            color = contentColor,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {


    var selectedItem by remember { mutableStateOf(navItems[0]) }

    MaterialTheme {
//        Surface(color = Color(0xFFF5F5F5)) {
//            CustomBottomNavBar(
//                selectedItem = selectedItem,
//                onItemSelected = { selectedItem = it }
//            )
//        }
    }
}