package com.cody.haievents.android.common.componets.theater

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.componets.LabelPosition
import com.cody.haievents.android.common.componets.SeatKey
import com.cody.haievents.android.common.componets.SeatRow

// --- layout knobs ---
private val ColumnGap: Dp = 24.dp      // visible space BETWEEN the 4 big columns
private val RowGap: Dp = 8.dp          // vertical gap between rows inside a column
private val SeatRowHeight: Dp = 40.dp  // enforce identical row height across all columns


private val BlockWidth: Dp? = null

@Composable
private fun FixedHeightSeatRow(
    label: String,
    seats: IntRange,
    labelPosition: LabelPosition,
    selectedSeats: Set<SeatKey>,
    bookedSeats: Set<SeatKey>,
    onSeatToggle: (SeatKey) -> Unit
) {
    val widthMod = if (BlockWidth != null) Modifier.width(BlockWidth) else Modifier.wrapContentWidth()

    Box(
        modifier = widthMod.height(SeatRowHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        SeatRow(
            label = label,
            seats = seats,
            labelPosition = labelPosition,
            selectedSeats = selectedSeats,
            bookedSeats = bookedSeats,
            onSeatToggle = onSeatToggle
        )
    }
}

@Composable
fun GaneshTheaterBlockFirst(
    selectedSeats: Set<SeatKey>,
    bookedSeats: Set<SeatKey> = emptySet(),
    onSeatToggle: (SeatKey) -> Unit
) {
    Row(
        modifier = Modifier
            .width(2000.dp)
            .height(2000.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(ColumnGap) // keep big gap between blocks
    ) {
        // ===== LEFT COLUMN (labels on the left) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            listOf("K","J","I","H").forEach { r ->
                FixedHeightSeatRow(r, 1..13, LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle)
            }
            listOf("G","F").forEach { r ->
                FixedHeightSeatRow(r, 1..12, LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle)
            }
            FixedHeightSeatRow("E", 1..11, LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("D", 1..10, LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("C", 1..9 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("B", 1..8 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("A", 1..5 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle)
        }

        // ===== MID COLUMN (no labels) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            listOf("K","J","I","H").forEach { r ->
                FixedHeightSeatRow(r, 14..28, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            }
            listOf("G","F").forEach { r ->
                FixedHeightSeatRow(r, 13..26, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            }
            FixedHeightSeatRow("E", 12..25, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("D", 11..24, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("C", 10..23, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("B",  9..22, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("A",  6..18, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
        }

        // ===== RIGHT COLUMN (no labels) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            listOf("K","J","I","H").forEach { r ->
                FixedHeightSeatRow(r, 29..43, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            }
            listOf("G","F").forEach { r ->
                FixedHeightSeatRow(r, 28..41, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            }
            FixedHeightSeatRow("E", 27..39, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("D", 26..37, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("C", 25..35, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("B", 24..33, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("A", 19..30, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle)
        }

        // ===== FAR RIGHT COLUMN (labels on the right) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.Start
        ) {
            FixedHeightSeatRow("K", 44..56, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("J", 43..54, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("I", 42..54, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("H", 43..54, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("G", 41..52, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("F", 41..51, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("E", 39..48, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("D", 36..47, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("C", 37..45, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("B", 36..43, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
            FixedHeightSeatRow("A", 31..36, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle)
        }
    }
}

@Preview(showBackground = true,)
@Composable
private fun GaneshTheaterBlockFirst_Preview() {
    var selected by remember { mutableStateOf(setOf<SeatKey>()) }
    val booked = remember { setOf(SeatKey("A", 1), SeatKey("A", 2), SeatKey("A", 3), SeatKey("A", 4)) }

    GaneshTheaterBlockFirst(
        selectedSeats = selected,
        bookedSeats = booked
    ) { key ->
        selected = if (key in selected) selected - key else selected + key
    }
}
