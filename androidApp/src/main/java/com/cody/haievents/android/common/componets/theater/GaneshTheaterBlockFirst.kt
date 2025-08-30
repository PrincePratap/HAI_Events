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

// --- layout knobs ---
private val ColumnGap: Dp = 24.dp      // visible space BETWEEN the 4 big columns
private val RowGap: Dp = 8.dp          // vertical gap between rows inside a column




@Composable
fun GaneshTheaterBlockFirst(
    response: GaneshTheaterGetSeatResponse,
    selectedSeats: Set<SeatKey>,
    bookedSeats: Set<SeatKey> = emptySet(),
    onSeatToggle: (SeatKey) -> Unit
) {
    val rowMeta = remember(response) {
        response.seatConfig.associateBy { it.row.uppercase() }
    }
    fun colorOf(row: String): String = rowMeta[row.uppercase()]?.color ?: "#e6f7ff"
    fun priceOf(row: String): String = rowMeta[row.uppercase()]?.price?.toString() ?: "0"

    Row(
        modifier = Modifier
            .width(2000.dp)
            .height(2000.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(ColumnGap)
    ) {
        // ===== LEFT COLUMN (labels on the left) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            listOf("K","J","I","H").forEach { r ->
                FixedHeightSeatRow2(r, 1..13, LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r))
            }
            listOf("G","F").forEach { r ->
                FixedHeightSeatRow2(r, 1..12, LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r))
            }
            FixedHeightSeatRow2("E", 1..11, LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("E"), price = priceOf("E"))
            FixedHeightSeatRow2("D", 1..10, LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("D"), price = priceOf("D"))
            FixedHeightSeatRow2("C", 1..9 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("C"), price = priceOf("C"))
            FixedHeightSeatRow2("B", 1..8 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A", 1..5 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }

        // ===== MID COLUMN (no labels) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            listOf("K","J","I","H").forEach { r ->
                FixedHeightSeatRow2(r, 14..28, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r))
            }
            listOf("G","F").forEach { r ->
                FixedHeightSeatRow2(r, 13..26, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r))
            }
            FixedHeightSeatRow2("E", 12..25, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("E"), price = priceOf("E"))
            FixedHeightSeatRow2("D", 11..24, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("D"), price = priceOf("D"))
            FixedHeightSeatRow2("C", 10..23, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("C"), price = priceOf("C"))
            FixedHeightSeatRow2("B",  9..22, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A",  6..18, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }

        // ===== RIGHT COLUMN (no labels) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            listOf("K","J","I","H").forEach { r ->
                FixedHeightSeatRow2(r, 29..43, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r))
            }
            listOf("G","F").forEach { r ->
                FixedHeightSeatRow2(r, 28..41, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r))
            }
            FixedHeightSeatRow2("E", 27..39, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("E"), price = priceOf("E"))
            FixedHeightSeatRow2("D", 26..37, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("D"), price = priceOf("D"))
            FixedHeightSeatRow2("C", 25..35, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("C"), price = priceOf("C"))
            FixedHeightSeatRow2("B", 24..33, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A", 19..30, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }

        // ===== FAR RIGHT COLUMN (labels on the right) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.Start
        ) {
            FixedHeightSeatRow2("K", 44..56, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("K"), price = priceOf("K"))
            FixedHeightSeatRow2("J", 43..54, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("J"), price = priceOf("J"))
            FixedHeightSeatRow2("I", 42..54, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("I"), price = priceOf("I"))
            FixedHeightSeatRow2("H", 43..54, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("H"), price = priceOf("H"))
            FixedHeightSeatRow2("G", 41..52, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("G"), price = priceOf("G"))
            FixedHeightSeatRow2("F", 41..51, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("F"), price = priceOf("F"))
            FixedHeightSeatRow2("E", 39..48, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("E"), price = priceOf("E"))
            FixedHeightSeatRow2("D", 36..47, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("D"), price = priceOf("D"))
            FixedHeightSeatRow2("C", 37..45, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("C"), price = priceOf("C"))
            FixedHeightSeatRow2("B", 36..43, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A", 31..36, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }
    }
}



@Preview(showBackground = true,)
@Composable
private fun GaneshTheaterBlockFirst_Preview() {
    var selected by remember { mutableStateOf(setOf<SeatKey>()) }
    val booked = remember { setOf(SeatKey("A", 1), SeatKey("A", 2), SeatKey("A", 3), SeatKey("A", 4)) }

//    GaneshTheaterBlockFirst(
//        selectedSeats = selected,
//        bookedSeats = booked
//    ) { key ->
//        selected = if (key in selected) selected - key else selected + key
//    }
}
