package com.cody.haievents.android.screens



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



// A data class to uniquely identify each seat
data class SeatId(val row: String, val number: Int)

@Composable
fun TheaterSeatingScreen() {
    // State to keep track of all selected seats
    var selectedSeats by rememberSaveable { mutableStateOf(setOf<SeatId>()) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "SELECT SEATS",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Create scroll states for both axes
            val verticalScrollState = rememberScrollState()
            val horizontalScrollState = rememberScrollState()

            // Outer container enables vertical scrolling
            Column(
                modifier = Modifier
                    .verticalScroll(verticalScrollState)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(horizontalScrollState),
                    contentAlignment = Alignment.TopCenter
                ) {
                    // This Column now holds the content that can be wider than the screen
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        StageBarrier()
                        Spacer(Modifier.height(16.dp))

                        SeatLayout(
                            selectedSeats = selectedSeats,
                            onSeatSelected = { seatId ->
                                selectedSeats = if (seatId in selectedSeats) {
                                    selectedSeats - seatId
                                } else {
                                    selectedSeats + seatId
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StageBarrier() {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(30.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Divider(color = Color.Gray, thickness = 1.dp)
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 4.dp))
    }
}

@Composable
fun SeatLayout(
    selectedSeats: Set<SeatId>,
    onSeatSelected: (SeatId) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        // Front Row (special layout)
        FrontRow(selectedSeats = selectedSeats, onSeatSelected = onSeatSelected)

        // Regular Rows
        SeatRow(label = "W", leftSeats = 1..10, rightSeats = 11..20, selectedSeats = selectedSeats, onSeatSelected = onSeatSelected)
        SeatRow(label = "V", leftSeats = 1..11, rightSeats = 12..22, selectedSeats = selectedSeats, onSeatSelected = onSeatSelected)
        SeatRow(label = "U", leftSeats = 1..13, rightSeats = 14..26, selectedSeats = selectedSeats, onSeatSelected = onSeatSelected)
        SeatRow(label = "T", leftSeats = 1..13, rightSeats = 14..26, selectedSeats = selectedSeats, onSeatSelected = onSeatSelected)
        SeatRow(label = "S", leftSeats = 1..10, rightSeats = 11..20, selectedSeats = selectedSeats, onSeatSelected = onSeatSelected)
    }
}

@Composable
fun FrontRow(
    selectedSeats: Set<SeatId>,
    onSeatSelected: (SeatId) -> Unit
) {
    // REMOVED: fillMaxWidth() and weights to allow the row to be wider than the screen
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Left section
        Row(horizontalArrangement = Arrangement.End) {
            WheelchairSeat(
                seatId = SeatId("X", 0),
                isSelected = SeatId("X", 0) in selectedSeats,
                onClick = onSeatSelected
            )
            Spacer(Modifier.width(4.dp))
            (1..5).reversed().forEach { seatNum ->
                Seat(
                    seatId = SeatId("X", seatNum),
                    isSelected = SeatId("X", seatNum) in selectedSeats,
                    onClick = onSeatSelected
                )
            }
        }

        // Tech Booth in the middle
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp) // Added padding to create space
                .width(100.dp)
                .height(24.dp)
                .border(1.dp, Color.Black)
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Divider(color = Color.Gray)
        }

        // Right section
        Row(horizontalArrangement = Arrangement.Start) {
            (6..9).forEach { seatNum ->
                Seat(
                    seatId = SeatId("X", seatNum),
                    isSelected = SeatId("X", seatNum) in selectedSeats,
                    onClick = onSeatSelected
                )
            }
            Spacer(Modifier.width(4.dp))
            WheelchairSeat(
                seatId = SeatId("X", 10),
                isSelected = SeatId("X", 10) in selectedSeats,
                onClick = onSeatSelected
            )
        }
    }
}

@Composable
fun SeatRow(
    label: String,
    leftSeats: IntRange,
    rightSeats: IntRange,
    selectedSeats: Set<SeatId>,
    onSeatSelected: (SeatId) -> Unit
) {
    // REMOVED: fillMaxWidth() to allow the row to be wider than the screen
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.width(24.dp), textAlign = TextAlign.Center)

        // Left seats
        // REMOVED: weight(1f)
        Row(horizontalArrangement = Arrangement.End) {
            leftSeats.reversed().forEach { seatNum ->
                Seat(
                    seatId = SeatId(label, seatNum),
                    isSelected = SeatId(label, seatNum) in selectedSeats,
                    onClick = onSeatSelected
                )
            }
        }

        // Aisle
        Spacer(Modifier.width(28.dp))

        // Right seats
        // REMOVED: weight(1f)
        Row(horizontalArrangement = Arrangement.Start) {
            rightSeats.forEach { seatNum ->
                Seat(
                    seatId = SeatId(label, seatNum),
                    isSelected = SeatId(label, seatNum) in selectedSeats,
                    onClick = onSeatSelected
                )
            }
        }
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.width(24.dp), textAlign = TextAlign.Center)
    }
}


@Composable
fun Seat(
    seatId: SeatId,
    isSelected: Boolean,
    onClick: (SeatId) -> Unit
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.Black

    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(width = 20.dp, height = 20.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .border(1.dp, Color.DarkGray, RoundedCornerShape(4.dp))
            .clickable { onClick(seatId) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seatId.number.toString(),
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold,
            color = contentColor
        )
    }
}

@Composable
fun WheelchairSeat(
    seatId: SeatId,
    isSelected: Boolean,
    onClick: (SeatId) -> Unit
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val iconColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.Black

    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(20.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(1.dp, Color.DarkGray, CircleShape)
            .clickable { onClick(seatId) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Wheelchair Accessible",
            modifier = Modifier.size(14.dp),
            tint = iconColor
        )
    }
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun TheaterSeatingScreenPreview() {
        TheaterSeatingScreen()

}