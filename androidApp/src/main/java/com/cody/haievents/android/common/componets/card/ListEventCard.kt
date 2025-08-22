package com.cody.haievents.android.common.componets.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.screens.listedEvents.ActionButton
import com.cody.haievents.android.screens.listedEvents.Event
import com.cody.haievents.android.screens.listedEvents.InfoRow
import com.cody.haievents.android.screens.listedEvents.StatusBadge


@Composable
fun ListEventCard(event: Event) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(id = event.imageRes),
                contentDescription = event.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = event.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    StatusBadge(status = event.status)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = event.organizer,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                InfoRow(icon = Icons.Default.LocationOn, text = event.location)
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(icon = Icons.Default.CalendarMonth, text = event.date)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = event.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ActionButton(
                        text = "Edit",
                        icon = Icons.Default.Edit,
                        containerColor = Color(0xFFE3F2FD),
                        contentColor = Color(0xFF1E88E5),
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        text = "Share",
                        icon = Icons.Default.Share,
                        containerColor = Color(0xFFE8F5E9),
                        contentColor = Color(0xFF388E3C),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}