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
import com.cody.haievents.android.common.componets.SeatRow



@Composable
 fun GaneshTheaterBlockSecond()  {
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
            SeatRow(label = "S", seats = 1..18)
            SeatRow(label = "R", seats = 1..18)
            SeatRow(label = "Q", seats = 1..17)
            SeatRow(label = "P", seats = 1..17)
            SeatRow(label = "O", seats = 1..12)
            SeatRow(label = "N", seats = 1..12)
            SeatRow(label = "M", seats = 1..12)
            SeatRow(label = "L", seats = 1..11)
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SeatRow(label = "S", seats = 19..34)
            SeatRow(label = "R", seats = 19..34)
            SeatRow(label = "Q", seats = 18..33)
            SeatRow(label = "P", seats = 18..33)
            SeatRow(label = "O", seats = 13..28)
            SeatRow(label = "N", seats = 13..28)
            SeatRow(label = "M", seats = 13..28)
            SeatRow(label = "L", seats = 12..26)
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SeatRow(label = "S", seats = 35..50)
            SeatRow(label = "R", seats = 35..50)
            SeatRow(label = "Q", seats = 34..49)
            SeatRow(label = "P", seats = 34..49)
            SeatRow(label = "O", seats = 29..44)
            SeatRow(label = "N", seats = 29..44)
            SeatRow(label = "M", seats = 29..44)
            SeatRow(label = "L", seats = 27..41)
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SeatRow(label = "S", seats = 35..34)
            SeatRow(label = "R", seats = 35..34)
            SeatRow(label = "Q", seats = 34..33)
            SeatRow(label = "P", seats = 34..33)
            SeatRow(label = "O", seats = 29..28)
            SeatRow(label = "N", seats = 29..28)
            SeatRow(label = "M", seats = 29..28)
            SeatRow(label = "L", seats = 17..26)
        }
    }
}


@Preview(showBackground = true, widthDp = 2000, heightDp = 2000)
@Composable
private fun GaneshTheaterBlockSecondPreview() {
    GaneshTheaterBlockSecond()
}