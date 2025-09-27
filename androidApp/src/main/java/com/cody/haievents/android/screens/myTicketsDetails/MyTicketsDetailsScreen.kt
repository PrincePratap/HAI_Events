package com.cody.haievents.android.screens.myTicketsDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cody.haievents.Show.model.MyTicketData
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.Show.model.MyTicketDetails
import com.cody.haievents.Show.model.MyTicketItem
import com.cody.haievents.android.screens.ShowDetailed.ExpandableText

// --- Public screen API (use this one from your Composable route) ---
@Composable
fun MyTicketsDetailsScreen(
    uiState: MyTicketsDetailsUiState,
    navigationBack: () -> Unit = {}
) {
    val details = uiState.myTicketDetails

    Scaffold(
        topBar = {
            CommonTopBar(
                title = details?.booking?.data?.title.orEmpty(),
                onBackClick = navigationBack
            )
        },
        containerColor = Color.White
    ) { padding ->
        if (details == null) {
            // Simple empty state (you can replace with a proper loader if isLoading)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading ticket details…")
            }
            return@Scaffold
        }

        TicketDetailsContent(
            details = details,
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        )
    }
}

// --- Internal content rendering MyTicketDetails only ---
@Composable
private fun TicketDetailsContent(
    details: MyTicketDetails,
    modifier: Modifier = Modifier
) {
    val d = details.booking.data

    Column(modifier = modifier.fillMaxWidth()) {
        // Banner
        AsyncImage(
            model = "https://haievents.com/${d.imagePath}",
            contentDescription = "Event Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2.1f),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {

            // Title
            Text(
                text = d.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(16.dp))

            // Venue / Date / Time
            InfoRowLabeled(
                icon = Icons.Outlined.LocationOn,
                label = "Venue",
                value = d.venue
            )
            Spacer(Modifier.height(8.dp))
            InfoRowLabeled(
                icon = Icons.Outlined.CalendarToday,
                label = "Date",
                value = d.date
            )
            Spacer(Modifier.height(8.dp))
            InfoRowLabeled(
                icon = Icons.Outlined.Schedule,
                label = "Time",
                value = d.time
            )

            Spacer(Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            Spacer(Modifier.height(16.dp))

            // Ticket block
            Text(
                text = "Your Ticket",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            KeyValueRow("Ticket", d.ticketName)
            KeyValueRow("Price", "₹${d.ticketPrice}")
            KeyValueRow("Quantity", d.purchasedQty.toString())
            KeyValueRow("Total", "₹${d.totalPrice}")

            Spacer(Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            Spacer(Modifier.height(16.dp))

            // Summary (About)
            Text(
                text = "About the Show",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            ExpandableText(
                text = d.summary,
                collapsedLines = 4
            )
        }
    }
}

// --- Small building blocks (local to this screen) ---

@Composable
private fun InfoRowLabeled(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .size(20.dp)
                .padding(top = 2.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun KeyValueRow(key: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, widthDp = 411, heightDp = 891, name = "Ticket Details – Data")
@Composable
fun Preview_MyTicketsDetails_Data() {
    val sample = MyTicketDetails(
        status = true,
        booking = MyTicketItem(
            type = "movie",
            data = MyTicketData(
                id = 52,
                title = "Kaifiyat",
                slug = "kaifiyat",
                imagePath = "img/movies/1756131029_OPEN MIC KKKK.png",
                categoryId = 1,
                date = "14 Dec, Sunday",
                time = "10:00 AM – 3:00 PM",
                summary = "H.A.I EVENTS presents 'KAIFIYAT' on 14th DEC 2025...\nMore summary here for preview purposes.",
                venue = "'Golden word studio' Metro station, C-373 Ground Floor Near Boon Medical, New Ashok Nagar, New Delhi, Delhi 110096",
                ticketName = "Attendee",
                ticketPrice = "1.00",
                purchasedQty = 1,
                totalPrice = 1
            )
        )
    )

    MyTicketsDetailsScreen(
        uiState = MyTicketsDetailsUiState(
            isLoading = false,
            errorMessage = null,
            succeed = true,
            myTicketDetails = sample
        ),
        navigationBack = {}
    )
}

@Preview(showBackground = true, widthDp = 411, heightDp = 891, name = "Ticket Details – Loading")
@Composable
fun Preview_MyTicketsDetails_Loading() {
    MyTicketsDetailsScreen(
        uiState = MyTicketsDetailsUiState(
            isLoading = true,
            errorMessage = null,
            succeed = false,
            myTicketDetails = null
        ),
        navigationBack = {}
    )
}



