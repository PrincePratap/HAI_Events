package com.cody.haievents.android.common.componets

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Seat identity (row + number) ---
data class SeatKey(val row: String, val number: Int)
enum class LabelPosition { LEFT, RIGHT, BOTH, NONE }

@Composable
fun Seat(
    number: Int,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val brand = Color(0xFF00A2B1)
    val bg by animateColorAsState(if (selected) brand.copy(alpha = 0.18f) else Color.Transparent)
    val borderColor by animateColorAsState(if (selected) brand else brand)
    val textColor by animateColorAsState(if (selected) brand else Color.Unspecified)

    Surface(
        modifier = Modifier
            .width(15.dp)
            .height(15.dp)
            .let { base ->
                if (enabled) base.clickable(role = Role.Checkbox, onClick = onClick) else base
            },
        shape = RoundedCornerShape(4.dp),
        color = bg,
        border = BorderStroke(1.dp, if (enabled) borderColor else Color.LightGray)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = number.toString(),
                fontSize = 5.sp,
                fontWeight = FontWeight.Bold,
                color = if (enabled) textColor else Color.LightGray
            )
        }
    }
}

@Composable
fun SeatRow(
    label: String,
    seats: IntRange,
    labelPosition: LabelPosition = LabelPosition.LEFT,
    selectedSeats: Set<SeatKey>,                    // which seats are selected
    bookedSeats: Set<SeatKey> = emptySet(),         // which seats are disabled
    onSeatToggle: (SeatKey) -> Unit                 // toggle callback
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (labelPosition == LabelPosition.LEFT || labelPosition == LabelPosition.BOTH) {
            Text(
                text = label,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }

        seats.forEach { number ->
            val key = SeatKey(label, number)
            val isSelected = key in selectedSeats
            val isBooked = key in bookedSeats

            Seat(
                number = number,
                selected = isSelected,
                enabled = !isBooked
            ) {
                onSeatToggle(key)
            }
        }

        if (labelPosition == LabelPosition.RIGHT || labelPosition == LabelPosition.BOTH) {
            Text(
                text = label,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
