package com.cody.haievents.android.common.componets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Seat(number: String) {
    Surface(
        modifier = Modifier
            .width(20.dp)
            .height(20.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, Color(0xFF00A2B1))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = number,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

enum class LabelPosition {
    LEFT, RIGHT, BOTH, NONE
}
@Composable
fun SeatRow(
    label: String,
    seats: IntRange,
    labelPosition: LabelPosition = LabelPosition.LEFT // default = left
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Left label if enabled
        if (labelPosition == LabelPosition.LEFT || labelPosition == LabelPosition.BOTH) {
            Text(
                text = label,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(24.dp)
            )
        }

        // Seats
        seats.forEach { number ->
            Seat(number = number.toString())
        }

        // Right label if enabled
        if (labelPosition == LabelPosition.RIGHT || labelPosition == LabelPosition.BOTH) {
            Text(
                text = label,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(24.dp)
            )
        }
    }
}