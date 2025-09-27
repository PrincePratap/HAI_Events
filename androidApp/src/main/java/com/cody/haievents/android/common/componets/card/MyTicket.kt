package com.cody.haievents.android.common.componets.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTicket(
    imageUrl: String = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?q=80&w=1600&auto=format",
    title: String = "Virtual Music Concert",
    byLine: String = "H.A.I Events",
    venue: String = "Kamani Auditorium, Delhi",
    date: String = "Sunday, 20 July 2025",
    time: String = "10:00 AM – 3:00 PM",
    onClick: (() -> Unit)? = null,             // 👈 Card click
    onVenueClick: (() -> Unit)? = null,        // 👈 Row clicks (optional)
    onDateClick: (() -> Unit)? = null,
    onTimeClick: (() -> Unit)? = null
) {
    // Use the clickable Card overload and toggle enabled based on onClick presence
    Card(
        onClick = { onClick?.invoke() },
        enabled = onClick != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // ---- Banner from URL ----
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = ColorPainter(Color(0xFFEFEFEF)),
                error = ColorPainter(Color(0xFFEFEFEF)),
                contentDescription = "Event Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = byLine,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ---- Info rows with optional click ----
                InfoRow(
                    icon = Icons.Outlined.LocationOn,
                    text = venue,
                    onClick = onVenueClick
                )
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(
                    icon = Icons.Outlined.Event,
                    text = date,
                    onClick = onDateClick
                )
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(
                    icon = Icons.Outlined.Schedule,
                    text = time,
                    onClick = onTimeClick
                )


                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun InfoRow(
    icon: ImageVector,
    text: String,
    onClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .then(
                if (onClick != null)
                    Modifier
                        .fillMaxWidth()
                        .clickable(role = Role.Button, onClick = onClick)
                else Modifier.fillMaxWidth()
            )
            .padding(vertical = 2.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewMyTicket() {
    MyTicket(
        imageUrl = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?q=80&w=1600&auto=format",
        title = "Open Mic Night",
        byLine = "H.A.I Events",
        venue = "Golden Word Studio, New Ashok Nagar, Delhi",
        date = "14 Dec, Sunday",
        time = "10:00 AM – 3:00 PM",
        onClick = { /* TODO: navigate to details */ },
        onVenueClick = { /* TODO: open maps */ },
        onDateClick = { /* TODO: add to calendar */ },
        onTimeClick = { /* TODO: show schedule tooltip */ }
    )
}
