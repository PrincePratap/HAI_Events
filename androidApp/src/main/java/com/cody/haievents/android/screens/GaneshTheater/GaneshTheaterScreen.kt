package com.cody.haievents.android.screens.GaneshTheater

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
private const val MIN_SCALE = 0.7f
private const val MAX_SCALE = 3f

@Composable
fun GaneshTheaterScreen() {
    val verticalScroll = rememberScrollState()
    val horizontalScroll = rememberScrollState()

    Box(
        modifier = Modifier
            .verticalScroll(verticalScroll)
            .horizontalScroll(horizontalScroll)
    ) {
        // Place your seat blocks here
        Column {
            BlockThird()
//            BlockSecond()
//            BlockFirst()
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

@Preview(showBackground = true, widthDp = 2000, heightDp = 2000)
@Composable
fun SeatBookingScreenPreview() {
     GaneshTheaterScreen()
}