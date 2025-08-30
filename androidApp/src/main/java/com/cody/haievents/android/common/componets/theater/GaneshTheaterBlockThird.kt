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
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.android.common.componets.FixedHeightSeatRow2
import com.cody.haievents.android.common.componets.LabelPosition
import com.cody.haievents.android.common.componets.SeatKey
import com.cody.haievents.android.common.componets.SeatRow

// ---- layout knobs (tune to match Seat size) ----
private val ColumnGap: Dp = 24.dp     // gap BETWEEN large columns
private val RowGap: Dp = 8.dp         // vertical space between rows




@Composable
fun GaneshTheaterBlockThird(
    response: GaneshTheaterGetSeatResponse,
    selectedSeats: Set<SeatKey> = emptySet(),
    bookedSeats: Set<SeatKey> = emptySet(),
    onSeatToggle: (SeatKey) -> Unit = {}
) {
    val rowMeta = remember(response) { response.seatConfig.associateBy { it.row.uppercase() } }
    fun colorOf(row: String)  = rowMeta[row.uppercase()]?.color ?: "#fff8dc" // gold default for Z–T
    fun priceOf(row: String)  = rowMeta[row.uppercase()]?.price?.toString() ?: "0"

    val rows = listOf("Z", "Y", "X", "W", "V", "U", "T")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(ColumnGap)
    ) {
        // Column 1 (labels on the LEFT) -> 1..14
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            rows.forEach { r ->
                FixedHeightSeatRow2(
                    r, 1..14, LabelPosition.LEFT,
                    selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r)
                )
            }
        }

        // Column 2 -> 15..28
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.Start
        ) {
            rows.forEach { r ->
                FixedHeightSeatRow2(
                    r, 15..28, LabelPosition.NONE,
                    selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r)
                )
            }
        }

        // Column 3 -> Z/Y/X: 29..41,  W/V/U/T: (29..31) + (32..34)
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            rows.forEach { r ->
                if (r in listOf("W", "V", "U", "T")) {
                    FixedHeightSeatRow2(
                        r, 29..31, LabelPosition.NONE,
                        selectedSeats, bookedSeats, onSeatToggle,
                        color = colorOf(r), price = priceOf(r)
                    )
                    FixedHeightSeatRow2(
                        r, 32..34, LabelPosition.NONE,
                        selectedSeats, bookedSeats, onSeatToggle,
                        color = colorOf(r), price = priceOf(r)
                    )
                } else {
                    FixedHeightSeatRow2(
                        r, 29..41, LabelPosition.NONE,
                        selectedSeats, bookedSeats, onSeatToggle,
                        color = colorOf(r), price = priceOf(r)
                    )
                }
            }
        }

        // Column 4 -> Z/Y/X: 42..55, else 35..48
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            rows.forEach { r ->
                val range = if (r in listOf("Z", "Y", "X")) 42..55 else 35..48
                FixedHeightSeatRow2(
                    r, range, LabelPosition.NONE,
                    selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r)
                )
            }
        }

        // Column 5 (labels on the RIGHT) -> Z/Y/X: 56..69, else 49..62
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            rows.forEach { r ->
                val range = if (r in listOf("Z", "Y", "X")) 56..69 else 49..62
                FixedHeightSeatRow2(
                    r, range, LabelPosition.RIGHT,
                    selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r)
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 1200, heightDp = 900)
@Composable
private fun GaneshTheaterBlockThirdPreview() {
//    var selected by remember { mutableStateOf(setOf<SeatKey>()) }
//    val booked = remember { setOf(SeatKey("Z", 15), SeatKey("T", 30)) }
//    GaneshTheaterBlockThird(
//        selectedSeats = selected,
//        bookedSeats = booked
//    ) { key ->
//        selected = if (key in selected) selected - key else selected + key
//    }
}
