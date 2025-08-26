package com.cody.haievents.android.common.componets.theater

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.componets.LabelPosition
import com.cody.haievents.android.common.componets.SeatRow


@Composable
private fun GaneshTheaterBlockFirst() {
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SeatRow(label = "K", seats = 14..28)
            SeatRow(label = "J", seats = 14..28)
            SeatRow(label = "I", seats = 14..28)
            SeatRow(label = "H", seats = 14..28)
            SeatRow(label = "G", seats = 13..26)
            SeatRow(label = "F", seats = 13..26)
            SeatRow(label = "E", seats = 12..25)
            SeatRow(label = "D", seats = 11..24)
            SeatRow(label = "C", seats = 10..23)
            SeatRow(label = "B", seats = 9..22)
            SeatRow(label = "A", seats = 6..18)
        }

        Spacer(Modifier.width(16.dp))

        // RIGHT GRID
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SeatRow(label = "K", seats = 14..28)
            SeatRow(label = "J", seats = 14..28)
            SeatRow(label = "I", seats = 14..28)
            SeatRow(label = "H", seats = 14..28)
            SeatRow(label = "G", seats = 13..26)
            SeatRow(label = "F", seats = 13..26)
            SeatRow(label = "E", seats = 12..25)
            SeatRow(label = "D", seats = 11..24)
            SeatRow(label = "C", seats = 10..23)
            SeatRow(label = "B", seats = 9..22)
            SeatRow(label = "A", seats = 6..18)
        }

        Spacer(Modifier.width(16.dp))

        // FAR RIGHT LABELS
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SeatRow(label = "K", seats = 44..56, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "J", seats = 43..54, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "I", seats = 42..54, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "H", seats = 43..54, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "G", seats = 41..52, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "F", seats = 41..51, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "E", seats = 39..48, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "D", seats = 36..47, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "C", seats = 37..45, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "B", seats = 36..43, labelPosition = LabelPosition.RIGHT)
            SeatRow(label = "A", seats = 31..36, labelPosition = LabelPosition.RIGHT)
        }
    }
}

@Preview(showBackground = true, widthDp = 2000, heightDp = 2000)
@Composable
private fun GaneshTheaterBlockFirst_Preview_Tablet() {
    GaneshTheaterBlockFirst()
}