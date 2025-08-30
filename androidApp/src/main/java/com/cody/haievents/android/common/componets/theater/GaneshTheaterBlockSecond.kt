package com.cody.haievents.android.common.componets.theater

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.android.common.componets.FixedHeightSeatRow2
import com.cody.haievents.android.common.componets.LabelPosition
import com.cody.haievents.android.common.componets.SeatKey
import com.cody.haievents.android.common.componets.SeatRow

// spacing knobs
private val ColumnGap: Dp = 24.dp
private val RowGap: Dp = 8.dp
private val SeatRowHeight: Dp = 40.dp



@Composable
fun GaneshTheaterBlockSecond(
    response: GaneshTheaterGetSeatResponse,
    selectedSeats: Set<SeatKey> = emptySet(),
    bookedSeats: Set<SeatKey> = emptySet(),
    onSeatToggle: (SeatKey) -> Unit = {}
) {
    // Build a quick map from row -> meta (case-insensitive)
    val rowMeta = remember(response) {
        response.seatConfig.associateBy { it.row.uppercase() }
    }
    fun colorOf(row: String): String = rowMeta[row.uppercase()]?.color ?: "#e6f7ff"
    fun priceOf(row: String): String = rowMeta[row.uppercase()]?.price?.toString() ?: "0"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(ColumnGap)
    ) {
        // LEFT column (labels on the left)
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            FixedHeightSeatRow2("S", 1..18, LabelPosition.LEFT,  selectedSeats, bookedSeats, onSeatToggle, color = colorOf("S"), price = priceOf("S"))
            FixedHeightSeatRow2("R", 1..18, LabelPosition.LEFT,  selectedSeats, bookedSeats, onSeatToggle, color = colorOf("R"), price = priceOf("R"))
            FixedHeightSeatRow2("Q", 1..17, LabelPosition.LEFT,  selectedSeats, bookedSeats, onSeatToggle, color = colorOf("Q"), price = priceOf("Q"))
            FixedHeightSeatRow2("P", 1..17, LabelPosition.LEFT,  selectedSeats, bookedSeats, onSeatToggle, color = colorOf("P"), price = priceOf("P"))
            FixedHeightSeatRow2("O", 1..12, LabelPosition.LEFT,  selectedSeats, bookedSeats, onSeatToggle, color = colorOf("O"), price = priceOf("O"))
            FixedHeightSeatRow2("N", 1..12, LabelPosition.LEFT,  selectedSeats, bookedSeats, onSeatToggle, color = colorOf("N"), price = priceOf("N"))
            FixedHeightSeatRow2("M", 1..12, LabelPosition.LEFT,  selectedSeats, bookedSeats, onSeatToggle, color = colorOf("M"), price = priceOf("M"))
            FixedHeightSeatRow2("L", 1..11, LabelPosition.LEFT,  selectedSeats, bookedSeats, onSeatToggle, color = colorOf("L"), price = priceOf("L"))
        }

        // MID-LEFT column
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FixedHeightSeatRow2("S", 19..34, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("S"), price = priceOf("S"))
            FixedHeightSeatRow2("R", 19..34, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("R"), price = priceOf("R"))
            FixedHeightSeatRow2("Q", 18..33, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("Q"), price = priceOf("Q"))
            FixedHeightSeatRow2("P", 18..33, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("P"), price = priceOf("P"))
            FixedHeightSeatRow2("O", 13..28, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("O"), price = priceOf("O"))
            FixedHeightSeatRow2("N", 13..28, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("N"), price = priceOf("N"))
            FixedHeightSeatRow2("M", 13..28, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("M"), price = priceOf("M"))
            FixedHeightSeatRow2("L", 12..26, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("L"), price = priceOf("L"))
        }

        // MID-RIGHT column
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FixedHeightSeatRow2("S", 35..50, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("S"), price = priceOf("S"))
            FixedHeightSeatRow2("R", 35..50, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("R"), price = priceOf("R"))
            FixedHeightSeatRow2("Q", 34..49, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("Q"), price = priceOf("Q"))
            FixedHeightSeatRow2("P", 34..49, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("P"), price = priceOf("P"))
            FixedHeightSeatRow2("O", 29..44, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("O"), price = priceOf("O"))
            FixedHeightSeatRow2("N", 29..44, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("N"), price = priceOf("N"))
            FixedHeightSeatRow2("M", 29..44, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("M"), price = priceOf("M"))
            FixedHeightSeatRow2("L", 27..41, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("L"), price = priceOf("L"))
        }

        // RIGHT column (labels on the right)
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.Start
        ) {
            FixedHeightSeatRow2("S", 51..68, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("S"), price = priceOf("S"))
            FixedHeightSeatRow2("R", 51..68, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("R"), price = priceOf("R"))
            FixedHeightSeatRow2("Q", 50..67, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("Q"), price = priceOf("Q"))
            FixedHeightSeatRow2("P", 50..66, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("P"), price = priceOf("P"))
            FixedHeightSeatRow2("O", 45..57, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("O"), price = priceOf("O"))
            FixedHeightSeatRow2("N", 45..56, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("N"), price = priceOf("N"))
            FixedHeightSeatRow2("M", 45..56, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("M"), price = priceOf("M"))
            FixedHeightSeatRow2("L", 42..53, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle, color = colorOf("L"), price = priceOf("L"))
        }
    }
}


@Preview(showBackground = true, widthDp = 2000, heightDp = 2000)
@Composable
private fun GaneshTheaterBlockSecond_Preview() {
//    var selected by remember { mutableStateOf(setOf<SeatKey>()) }
//    val booked = remember { setOf(SeatKey("S", 1), SeatKey("P", 20)) }
//
//    GaneshTheaterBlockSecond(
//        selectedSeats = selected,
//        bookedSeats = booked
//    ) { key ->
//        selected = if (key in selected) selected - key else selected + key
//    }
}
