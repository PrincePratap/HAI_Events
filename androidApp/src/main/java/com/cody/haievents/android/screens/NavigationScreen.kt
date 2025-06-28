package com.cody.haievents.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cody.haievents.android.common.componets.AppBottomNavigationBar
import com.cody.haievents.android.common.componets.AppHeader


private val screenBackgroundColor = Color(0xFF000000)

@Composable
fun  NavigationScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(screenBackgroundColor)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Your main content
            AppHeader()
            // Add Spacer to push bottom nav to bottom
            Box(modifier = Modifier.weight(1f)) {
                HomeScreen() // Replace with your main content composable
            }
            AppBottomNavigationBar() // This will stay at the bottom
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationScreenPreview() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(screenBackgroundColor)
        ) {
            NavigationScreen()
        }
    }
}

