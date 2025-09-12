package com.cody.haievents.android.common.componets.theater

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column// Add these imports if missing:
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.cody.haievents.Show.model.SeatRowConfig
import com.cody.haievents.android.common.componets.FixedHeightSeatRow2
import com.cody.haievents.android.common.componets.LabelPosition
import com.cody.haievents.android.common.componets.SeatKey

private val ColumnGap: Dp = 24.dp      // visible space BETWEEN the 4 big columns
private val RowGap: Dp = 8.dp

@Composable
fun GaneshTheaterBlockFourth(
    response: GaneshTheaterGetSeatResponse ,
    selectedSeats: Set<SeatKey> = emptySet(),
    bookedSeats: Set<SeatKey> = emptySet(),
    onSeatToggle: (SeatKey) -> Unit = {}
) {
    val rowMeta = remember(response) {
        response.seatConfig.associateBy { it.row.uppercase() }
    }
    fun colorOf(row: String): String = rowMeta[row.uppercase()]?.color ?: "#e6f7ff"
    fun priceOf(row: String): String = rowMeta[row.uppercase()]?.price?.toString() ?: "0"

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(ColumnGap)
    ) {
        // ===== LEFT COLUMN (labels on the left) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            FixedHeightSeatRow2("B", 1..9 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A", 1..9 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FixedHeightSeatRow2("I",  1..4, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("J"), price = priceOf("J"))
            FixedHeightSeatRow2("I",  1..3, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("I"), price = priceOf("I"))
            listOf("C","D","F","G","H").forEach { r ->
                FixedHeightSeatRow2(r, 1..4, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                    color = colorOf(r), price = priceOf(r))
            }
            FixedHeightSeatRow2("B",  10..13, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A",  10..13, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }

        // ===== RIGHT COLUMN (no labels) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FixedHeightSeatRow2(
                "J", 22..36, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("J"), price = priceOf("J")
            )
            FixedHeightSeatRow2(
                "I", 20..34, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("I"), price = priceOf("I")
            )
            FixedHeightSeatRow2(
                "H", 22..35, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("H"), price = priceOf("H")
            )

            FixedHeightSeatRow2(
                "C", 22..35, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("C"), price = priceOf("C")
            )
            FixedHeightSeatRow2(
                "D", 21..35, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("D"), price = priceOf("D")
            )
            FixedHeightSeatRow2(
                "F", 21..34, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("F"), price = priceOf("F")
            )
            FixedHeightSeatRow2(
                "G", 21..34, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("G"), price = priceOf("G")
            )

            FixedHeightSeatRow2(
                "B", 30..43, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B")
            )
            FixedHeightSeatRow2(
                "A", 29..42, LabelPosition.NONE, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A")
            )
        }


        // ===== FAR RIGHT COLUMN (labels on the right) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.Start
        ) {
            // Top rows in the crop
            FixedHeightSeatRow2("K", 37..52, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("K"), price = priceOf("K"))
            FixedHeightSeatRow2("J", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("J"), price = priceOf("J"))
            FixedHeightSeatRow2("I", 36..51, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("I"), price = priceOf("I"))
            FixedHeightSeatRow2("H", 36..51, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("H"), price = priceOf("H"))
            FixedHeightSeatRow2("G", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("G"), price = priceOf("G"))
            FixedHeightSeatRow2("F", 36..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("F"), price = priceOf("F"))
            FixedHeightSeatRow2("E", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("E"), price = priceOf("E"))

            // Bottom two rows in the crop
            FixedHeightSeatRow2("B", 44..59, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A", 43..57, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }

        // ===== FAR RIGHT COLUMN (labels on the right) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.Start
        ) {
            // Top rows in the crop
            FixedHeightSeatRow2("K", 37..52, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("K"), price = priceOf("K"))
            FixedHeightSeatRow2("J", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("J"), price = priceOf("J"))
            FixedHeightSeatRow2("I", 36..51, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("I"), price = priceOf("I"))
            FixedHeightSeatRow2("H", 36..51, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("H"), price = priceOf("H"))
            FixedHeightSeatRow2("G", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("G"), price = priceOf("G"))
            FixedHeightSeatRow2("F", 36..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("F"), price = priceOf("F"))
            FixedHeightSeatRow2("E", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("E"), price = priceOf("E"))

            // Bottom two rows in the crop
            FixedHeightSeatRow2("B", 44..59, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A", 43..57, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }
        // ===== FAR RIGHT COLUMN (labels on the right) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.Start
        ) {
            // Top rows in the crop
            FixedHeightSeatRow2("K", 37..52, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("K"), price = priceOf("K"))
            FixedHeightSeatRow2("J", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("J"), price = priceOf("J"))
            FixedHeightSeatRow2("I", 36..51, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("I"), price = priceOf("I"))
            FixedHeightSeatRow2("H", 36..51, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("H"), price = priceOf("H"))
            FixedHeightSeatRow2("G", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("G"), price = priceOf("G"))
            FixedHeightSeatRow2("F", 36..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("F"), price = priceOf("F"))
            FixedHeightSeatRow2("E", 35..50, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("E"), price = priceOf("E"))

            // Bottom two rows in the crop
            FixedHeightSeatRow2("B", 60..63, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A", 58..61, LabelPosition.RIGHT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }
        // ===== FAR RIGHT COLUMN (labels on the right) =====
        Column(
            verticalArrangement = Arrangement.spacedBy(RowGap),
            horizontalAlignment = Alignment.End
        ) {
            FixedHeightSeatRow2("B", 64..71 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("B"), price = priceOf("B"))
            FixedHeightSeatRow2("A", 62..69 , LabelPosition.LEFT, selectedSeats, bookedSeats, onSeatToggle,
                color = colorOf("A"), price = priceOf("A"))
        }


    }
}






@Preview(
    name = "Ganesh Theater – Scrollable Preview",
    showBackground = true,
    widthDp = 2000,
    heightDp = 2000
)
@Composable
private fun GaneshTheaterBlockFourth_Preview() {
    var selected by remember { mutableStateOf(setOf<SeatKey>()) }
    val booked = remember {
        setOf(
            SeatKey("A", 1), SeatKey("A", 2),
            SeatKey("E", 5), SeatKey("K", 12)
        )
    }

    // Minimal seat-config with rows A..K so color/price lookups work
    val sampleResponse = remember {
        GaneshTheaterGetSeatResponse(
            status = true,
            seatConfig = listOf(
                SeatRowConfig("A", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("B", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("C", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("D", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("E", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("F", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("G", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("H", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("I", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("J", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
                SeatRowConfig("K", "STD", "#FFFFFF", 499, emptyList(), emptyList()),
            )
        )
    }

    // Scrollable wrapper for Preview so you can pan around the 2000x2000dp content
    val vScroll = rememberScrollState()
    val hScroll = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .horizontalScroll(hScroll)
            .verticalScroll(vScroll)
    ) {
        GaneshTheaterBlockFourth(
            response = sampleResponse,
            selectedSeats = selected,
            bookedSeats = booked,
            onSeatToggle = { key ->
                selected = if (key in selected) selected - key else selected + key
            }
        )
    }
}
