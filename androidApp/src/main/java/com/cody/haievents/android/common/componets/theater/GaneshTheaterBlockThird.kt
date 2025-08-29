package com.cody.haievents.android.common.componets.theater

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.componets.LabelPosition
import com.cody.haievents.android.common.componets.SeatKey
import com.cody.haievents.android.common.componets.SeatRow

// ---- layout knobs (tune to match Seat size) ----
private val ColumnGap: Dp = 24.dp     // gap BETWEEN large columns
private val RowGap: Dp = 8.dp         // vertical space between rows
private val SeatRowHeight: Dp = 40.dp // fixed row height = alignment across columns
private val InnerAisleGap: Dp = 45.dp // gap inside a column when a row has 2 segments

// A single row item with fixed height. Renders 1..N or multiple ranges with an inner aisle.
@Composable
private fun FixedHeightRowWithSegments(
    label: String,
    ranges: List<IntRange>,                 // one or two segments inside this column for this row
    labelPosition: LabelPosition,
    selectedSeats: Set<SeatKey>,
    bookedSeats: Set<SeatKey>,
    onSeatToggle: (SeatKey) -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(SeatRowHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        if (ranges.size == 1) {
            SeatRow(
                label = label,
                seats = ranges.first(),
                labelPosition = labelPosition,
                selectedSeats = selectedSeats,
                bookedSeats = bookedSeats,
                onSeatToggle = onSeatToggle
            )
        } else {
            // two segments with an inner aisle gap, label shown only on left-most segment
            Row(verticalAlignment = Alignment.CenterVertically) {
                SeatRow(
                    label = label,
                    seats = ranges[0],
                    labelPosition = labelPosition,
                    selectedSeats = selectedSeats,
                    bookedSeats = bookedSeats,
                    onSeatToggle = onSeatToggle
                )
                Spacer(Modifier.width(InnerAisleGap))
                SeatRow(
                    label = label,
                    seats = ranges[1],
                    labelPosition = LabelPosition.NONE,
                    selectedSeats = selectedSeats,
                    bookedSeats = bookedSeats,
                    onSeatToggle = onSeatToggle
                )
            }
        }
    }
}

/**
 * BlockThird – five columns:
 *  C1:  1..14      (labels LEFT)
 *  C2:  15..28     (no labels)
 *  C3:  29..41 or split (29..31 + 32..34) for W/V/U/T (no labels)
 *  C4:  42..55 for Z/Y/X, else 35..48 (no labels)
 *  C5:  56..69 for Z/Y/X, else 49..62 (labels RIGHT)
 */
@Composable
fun GaneshTheaterBlockThird(
    selectedSeats: Set<SeatKey> = emptySet(),
    bookedSeats: Set<SeatKey> = emptySet(),
    onSeatToggle: (SeatKey) -> Unit = {}
) {
    // row order, top -> bottom
    val rows = listOf("Z", "Y", "X", "W", "V", "U", "T")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(ColumnGap)
    ) {
        // --- Column 1 (labels on the LEFT) ---
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            rows.forEach { r ->
                FixedHeightRowWithSegments(
                    label = r,
                    ranges = listOf(1..14),
                    labelPosition = LabelPosition.LEFT,
                    selectedSeats = selectedSeats,
                    bookedSeats = bookedSeats,
                    onSeatToggle = onSeatToggle
                )
            }
        }

        // --- Column 2 ---
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.Start
        ) {
            rows.forEach { r ->
                FixedHeightRowWithSegments(
                    label = r,
                    ranges = listOf(15..28),
                    labelPosition = LabelPosition.NONE,
                    selectedSeats = selectedSeats,
                    bookedSeats = bookedSeats,
                    onSeatToggle = onSeatToggle
                )
            }
        }

        // --- Column 3 (has inner aisle for W/V/U/T only) ---
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            rows.forEach { r ->
                val ranges =
                    if (r in listOf("W", "V", "U", "T")) listOf(29..31, 32..34) else listOf(29..41)
                FixedHeightRowWithSegments(
                    label = r,
                    ranges = ranges,
                    labelPosition = LabelPosition.NONE,
                    selectedSeats = selectedSeats,
                    bookedSeats = bookedSeats,
                    onSeatToggle = onSeatToggle
                )
            }
        }

        // --- Column 4 ---
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            rows.forEach { r ->
                val range = if (r in listOf("Z", "Y", "X")) 42..55 else 35..48
                FixedHeightRowWithSegments(
                    label = r,
                    ranges = listOf(range),
                    labelPosition = LabelPosition.NONE,
                    selectedSeats = selectedSeats,
                    bookedSeats = bookedSeats,
                    onSeatToggle = onSeatToggle
                )
            }
        }

        // --- Column 5 (labels on the RIGHT) ---
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            rows.forEach { r ->
                val range = if (r in listOf("Z", "Y", "X")) 56..69 else 49..62
                FixedHeightRowWithSegments(
                    label = r,
                    ranges = listOf(range),
                    labelPosition = LabelPosition.RIGHT,
                    selectedSeats = selectedSeats,
                    bookedSeats = bookedSeats,
                    onSeatToggle = onSeatToggle
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1200, heightDp = 900)
@Composable
private fun GaneshTheaterBlockThirdPreview() {
    var selected by remember { mutableStateOf(setOf<SeatKey>()) }
    val booked = remember { setOf(SeatKey("Z", 15), SeatKey("T", 30)) }
    GaneshTheaterBlockThird(
        selectedSeats = selected,
        bookedSeats = booked
    ) { key ->
        selected = if (key in selected) selected - key else selected + key
    }
}
