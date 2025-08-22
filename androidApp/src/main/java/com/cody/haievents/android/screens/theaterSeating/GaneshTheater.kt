package com.cody.haievents.android.screens.theaterSeating

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.componets.Seat
import com.cody.haievents.android.common.componets.SeatRow

 private const val MIN_SCALE = 0.7f
private const val MAX_SCALE = 3f

@Composable
fun SeatBookingScreen() {
    // State for scale (zoom level) and offsets (pan position)
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            // Important: clip to bounds to prevent the content from drawing outside the Box
            .clipToBounds()
    ) {
        // This Column holds the entire seating chart. It will be scaled and translated.
        Column(
            modifier = Modifier
                // Apply the scale and translation transformations
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
                // Detect pinch-to-zoom and drag gestures
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        // Update the scale, clamping it within our min/max limits
                        scale = (scale * zoom).coerceIn(MIN_SCALE, MAX_SCALE)

                        // Update the offsets for panning
                        offsetX += pan.x * scale
                        offsetY += pan.y * scale
                    }
                }
                .fillMaxSize(), // Take up the full space for gesture detection
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Replaced LazyColumn with a standard Column
            BlockThird()
            BlockSecond()
            BlockFirst()
        }

        // Overlay for the zoom-in and zoom-out buttons
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Zoom In Button
            IconButton(onClick = {
                scale = (scale + 0.1f).coerceIn(MIN_SCALE, MAX_SCALE)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Zoom In")
            }
            // Zoom Out Button
            IconButton(onClick = {
                scale = (scale - 0.1f).coerceIn(MIN_SCALE, MAX_SCALE)
            }) {
                Icon(Icons.Default.Remove, contentDescription = "Zoom Out")
            }
        }
    }
}

@Composable
private fun BlockFirst() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center // Center the block for a better default view
    ) {
        // LEFT COLUMN (Labels)
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            SeatRow(label = "K", seats = 1..13)
            SeatRow(label = "J", seats = 1..13)
            SeatRow(label = "I", seats = 1..13)
            SeatRow(label = "H", seats = 1..13)
            SeatRow(label = "G", seats = 1..12)
            SeatRow(label = "F", seats = 1..12)
            SeatRow(label = "E", seats = 1..11)
            SeatRow(label = "D", seats = 1..10)
            SeatRow(label = "C", seats = 1..9)
            SeatRow(label = "B", seats = 1..8)
            SeatRow(label = "A", seats = 1..5)
        }

        Spacer(Modifier.width(16.dp))

        // MID GRID
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (12..25).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (11..24).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (10..23).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (9..22).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (6..18).forEach { Seat(number = it.toString()) } }
        }

        Spacer(Modifier.width(16.dp))

        // RIGHT GRID
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (12..25).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (11..24).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (10..23).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (9..22).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (6..18).forEach { Seat(number = it.toString()) } }
        }

        Spacer(Modifier.width(16.dp))

        // FAR RIGHT LABELS
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SeatRow(label = "K", seats = 1..13)
            SeatRow(label = "J", seats = 1..13)
            SeatRow(label = "I", seats = 1..13)
            SeatRow(label = "H", seats = 1..12)
            SeatRow(label = "G", seats = 1..12)
            SeatRow(label = "F", seats = 1..11)
            SeatRow(label = "E", seats = 1..10)
            SeatRow(label = "D", seats = 1..9)
            SeatRow(label = "C", seats = 1..8)
            SeatRow(label = "B", seats = 1..5)
        }
    }
}

@Composable
private fun BlockSecond() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center // Center the block for a better default view
    ) {
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            SeatRow(label = "S", seats = 1..13)
            SeatRow(label = "R", seats = 1..12)
            SeatRow(label = "Q", seats = 1..12)
            SeatRow(label = "P", seats = 1..11)
            SeatRow(label = "O", seats = 1..10)
            SeatRow(label = "N", seats = 1..9)
            SeatRow(label = "M", seats = 1..8)
            SeatRow(label = "L", seats = 1..5)
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (12..25).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (11..24).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (10..23).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (9..22).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (6..18).forEach { Seat(number = it.toString()) } }
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (12..25).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (11..24).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (10..23).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (9..22).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (6..18).forEach { Seat(number = it.toString()) } }
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SeatRow(label = "K", seats = 1..13)
            SeatRow(label = "J", seats = 1..13)
            SeatRow(label = "I", seats = 1..13)
            SeatRow(label = "H", seats = 1..12)
            SeatRow(label = "G", seats = 1..12)
            SeatRow(label = "F", seats = 1..11)
            SeatRow(label = "E", seats = 1..10)
            SeatRow(label = "D", seats = 1..9)
            SeatRow(label = "C", seats = 1..8)
            SeatRow(label = "B", seats = 1..5)
        }
    }
}

@Composable
private fun BlockThird() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center // Center the block for a better default view
    ) {
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            SeatRow(label = "Z", seats = 1..14)
            SeatRow(label = "Y", seats = 1..14)
            SeatRow(label = "X", seats = 1..14)
            SeatRow(label = "W", seats = 1..14)
            SeatRow(label = "V", seats = 1..14)
            SeatRow(label = "U", seats = 1..14)
            SeatRow(label = "T", seats = 1..14)
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (12..25).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (11..24).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (10..23).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (9..22).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (6..18).forEach { Seat(number = it.toString()) } }
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (14..28).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (13..26).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (12..25).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (11..24).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (10..23).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (9..22).forEach { Seat(number = it.toString()) } }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { (6..18).forEach { Seat(number = it.toString()) } }
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SeatRow(label = "K", seats = 1..13)
            SeatRow(label = "J", seats = 1..13)
            SeatRow(label = "I", seats = 1..13)
            SeatRow(label = "H", seats = 1..12)
            SeatRow(label = "G", seats = 1..12)
            SeatRow(label = "F", seats = 1..11)
            SeatRow(label = "E", seats = 1..10)
            SeatRow(label = "D", seats = 1..9)
            SeatRow(label = "C", seats = 1..8)
            SeatRow(label = "B", seats = 1..5)
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 600)
@Composable
fun SeatBookingScreenPreview() {
    MaterialTheme { SeatBookingScreen() }
}