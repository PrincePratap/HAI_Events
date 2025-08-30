package com.cody.haievents.android.common.componets

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
data class SeatKey(val row: String, val number: Int ,val price: String = "0" ,val color : String = "")
enum class LabelPosition { LEFT, RIGHT, BOTH, NONE }

@Composable
fun Seat(
    number: Int,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
    color: String = "#4CAF50",
) {
    // Row's base color (from API/row)
    val rowBrand = remember(color) {
        try { Color(android.graphics.Color.parseColor(color)) }
        catch (_: IllegalArgumentException) { Color(0xFF00A2B1) } // fallback
    }

    // Use green when selected
    val selectedGreen = Color(0xFF4CAF50) // Material Green 500
    val activeBrand = if (selected) selectedGreen else rowBrand

    val bg by animateColorAsState(if (selected) activeBrand.copy(alpha = 0.18f) else Color.Transparent)
    val borderColor by animateColorAsState(if (enabled) activeBrand else Color.LightGray)
    val textColor by animateColorAsState(if (selected) activeBrand else Color.Unspecified)

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
    selectedSeats: Set<SeatKey>,
    bookedSeats: Set<SeatKey> = emptySet(),
    onSeatToggle: (SeatKey) -> Unit,
    color: String = "#00A2B1",
    price: String = "0"
) {

    // format price (₹ + Indian grouping if numeric)
    val priceText = remember(price) {
        price.toLongOrNull()?.let {
            "₹" + java.text.NumberFormat.getNumberInstance(java.util.Locale("en", "IN")).format(it)
        } ?: "₹$price"
    }

    @Composable
    fun LabelWithPrice() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(2.dp))
            Text(
                text = priceText,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (labelPosition == LabelPosition.LEFT || labelPosition == LabelPosition.BOTH) {
            LabelWithPrice()
        }

        seats.forEach { number ->
            val key = SeatKey(label, number)
            val isSelected = key in selectedSeats
            val isBooked = key in bookedSeats

            Seat(
                number = number,
                selected = isSelected,
                enabled = !isBooked,
                color = color,
                onClick = { onSeatToggle(key) }
            )
        }

        if (labelPosition == LabelPosition.RIGHT || labelPosition == LabelPosition.BOTH) {
            LabelWithPrice()
        }
    }
}



@Composable
 fun FixedHeightSeatRow2(
    label: String,
    seats: IntRange,
    labelPosition: LabelPosition,
    selectedSeats: Set<SeatKey>,
    bookedSeats: Set<SeatKey>,
    onSeatToggle: (SeatKey) -> Unit,
    color: String = "#e6f7ff",
    price: String = "0"

    ) {
    Box(
        modifier = Modifier.wrapContentWidth().height(40.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        SeatRow(
            label = label,
            seats = seats,
            labelPosition = labelPosition,
            selectedSeats = selectedSeats,
            bookedSeats = bookedSeats,
            onSeatToggle = onSeatToggle,
            color = color,
            price = price
        )
    }
}


