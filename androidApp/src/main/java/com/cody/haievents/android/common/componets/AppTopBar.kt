package com.cody.haievents.android.common.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.theming.GoldenYellow


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.cody.haievents.android.common.componets.SearchBarCommonNonClickable


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices


@Composable
fun CommonTopBar(
    title: String = "Open Mic",
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Surface(
        color = GoldenYellow,
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                text = title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommonTopBarPreview() {
    CommonTopBar(
        title = "Event Details",
        onBackClick = {

        }
    )
}


@Composable
fun HomeScreenHeader(
    clickOnSearch : () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .background(Color(0xFFC9A84F)) // A mustard/gold color matching the image
            .padding(20.dp)
    ) {
        // Top section with Greeting and Notification Icon
        HeaderSection()
        // Search Bar
        Spacer(modifier = Modifier.height(28.dp))

        SearchBarCommonNonClickable(
            value = "Search for Shows, events, artists...",
            onClick = clickOnSearch
        )


    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f) // Allow text to wrap if screen is small
        ) {
            Text(
                text = "Find events that match your vibe",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Delhi",
                    color = Color.White,
                    fontSize = 15.sp
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Change location",
                    tint = Color.White
                )
            }
        }
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifications",
            tint = Color.White,
            modifier = Modifier
                .padding(start = 16.dp) // Ensure space from text
                .size(30.dp)
        )
    }
}


@Preview(name = "Small Phone", device = Devices.PIXEL_3A, showBackground = true)
@Preview(name = "Large Phone", device = Devices.PIXEL_7_PRO, showBackground = true)
@Composable
fun HomeScreenPreview() {
    // A Surface is good practice for the top-level container.
    HomeScreenHeader()

}


@Composable
fun TheatreShowsScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(1f) // Takes up available space
                    .padding(start = 16.dp, top = 24.dp, end = 16.dp)
            ) {
                // "THEATRE SHOWS" Header
                TheatreShowsHeader()
                Spacer(modifier = Modifier.height(20.dp))
                // Row for the cards
                Row {
                    TheatreEventCard()
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            // Dark vertical bar on the right side as seen in the original image
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                   .background(Color(0xFF303030))
//            )
        }
    }
}

@Composable
fun TheatreShowsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "THEATRE SHOWS",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Underline for the title
            Divider(
                color = Color(0xFFFACC15), // A gold-like yellow color
                thickness = 2.5.dp,
                modifier = Modifier.width(140.dp)
            )
        }
        Text(
            text = "View All",
            color = Color.DarkGray,
            fontSize = 14.sp
        )
    }
}


@Composable
fun TheatreEventCard() {
    Card(
        modifier = Modifier.width(320.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.DarkGray) // Placeholder background
            ) {
                Image(

                    painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Using a system drawable as a placeholder
                    contentDescription = "The Last Leaf - A Live Drama",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.5f // To simulate a placeholder feel
                )
                // In a real app, the above Image would be used instead of this text.
                Text(
                    "Replace with actual image",
                    Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }


            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "The Last Leaf – A Live Drama",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "The Verse Lounge, Delhi",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Date",
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "21 July, Wednesday",
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Icon(
                        imageVector = Icons.Default.WatchLater,
                        contentDescription = "Time",
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "6:30 PM – 7:00 PM",
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}


@Preview()
@Composable
fun DefaultPreview() {
    TheatreShowsScreen()
}


