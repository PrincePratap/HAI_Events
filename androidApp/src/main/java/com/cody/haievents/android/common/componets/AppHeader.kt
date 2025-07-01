package com.cody.haievents.android.common.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.theming.LargeTextSize


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader() {
    val locationTextColor = Color(0xFFE53935)
    val badgeColor = Color(0xFFE53935)
    val iconColor = Color.Black.copy(alpha = 0.8f)

    Column(modifier = Modifier.background(Color.White)) {
        // Top border line
        Divider(color = Color.Gray.copy(alpha = 0.2f), thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left side: Title and Location
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Hai Events ",
                    color = locationTextColor,
                    fontSize = LargeTextSize,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { /* Handle location change */ }
                )
            }

            // Right side: Action Icons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Search Icon
                IconButton(onClick = { /* Handle search click */ }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = iconColor
                    )
                }

                // Notification Icon with Badge
                BadgedBox(
                    badge = {
                        Badge(
                            containerColor = badgeColor,
                            contentColor = Color.White
                        ) {
                            Text("2")
                        }
                    }
                ) {
                    IconButton(onClick = { /* Handle notifications click */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifications",
                            tint = iconColor
                        )
                    }
                }

//                // QR Scanner Icon
//                IconButton(onClick = { /* Handle QR scanner click */ }) {
//                    Icon(
//                        imageVector = Icons.Outlined.QrCodeScanner,
//                        contentDescription = "Scan QR Code",
//                        tint = iconColor
//                    )
//                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppHeaderPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                AppHeader()
            }
        }
    }
}