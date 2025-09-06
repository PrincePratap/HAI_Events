package com.cody.haievents.android.common.componets.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val goldColor = Color(0xFFC9A24C)
//private val lightGoldColor = Color(0xFFE6C47C)
private val screenBackground = Color(0xFFF7F7F7)
private val disabledTextGray = Color(0xFFBDBDBD)
//private val disabledButtonBackground = Color(0xFFE0E0E0)
private val primaryTextColor = Color(0xFF333333)
private val  dividerColor = Color.Black.copy(alpha = 0.08f)


@Composable
fun TicketCard(
    title: String,
    price: String,
    features: List<String>,
    enabled: Boolean = true,
    buttonContent: @Composable () -> Unit
) {
    val contentColor = if (enabled) primaryTextColor else disabledTextGray

    Column(modifier = Modifier.fillMaxWidth()) {

        // HEADER: left info (weighted) + right action
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),                     // ← takes remaining width
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = contentColor
                )
                Text(
                    text = price,
                    fontSize = 18.sp,
                    color = contentColor
                )
            }

            // Right-aligned button
            Box(modifier = Modifier.padding(start = 12.dp)) {
                buttonContent()
            }
        }

        // FEATURES
        Column(modifier = Modifier.wrapContentHeight()) {
            Spacer(modifier = Modifier.height(8.dp))
            features.forEach { feature ->
                Row(verticalAlignment = Alignment.Top) {
                    Text(text = "• ", color = contentColor, fontSize = 14.sp)
                    Text(
                        text = feature,
                        color = contentColor,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        HorizontalDivider(color = dividerColor)
    }
}


@Composable
fun AddButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = goldColor),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
    ) {
        Text("Add", color = Color.White)
    }
}

@Composable
fun QuantitySelector(quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(goldColor)
            .padding(horizontal = 4.dp, vertical = 2.dp)
    ) {
        TextButton(
            onClick = { if (quantity > 0) onQuantityChange(quantity - 1) },
            modifier = Modifier.size(32.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("-", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Text(
            text = quantity.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        TextButton(
            onClick = { onQuantityChange(quantity + 1) },
            modifier = Modifier.size(32.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("+", color = Color.White, fontSize = 20.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TicketCardAllStatesPreview() {
    Column(Modifier.background(Color.White)) {
        // State 1: Default, available to be added
        TicketCard(
            title = "General Admission",
            price = "$50.00",
            features = listOf("Access to main event hall", "Includes one complimentary drink"),
            enabled = true,
            buttonContent = { AddButton(onClick = {}) }
        )
        HorizontalDivider(color = dividerColor)

        // State 2: Selected, with quantity
//        TicketCard(
//            title = "VIP Pass",
//            price = "$120.00",
//            features = listOf(
//                "All General Admission perks",
//                "Access to VIP lounge",
//                "Free coat check"
//            ),
//            enabled = true,
//            buttonContent = { QuantitySelector(quantity = 2, onQuantityChange = {}) }
//        )
        HorizontalDivider(color = dividerColor)

        // State 3: Disabled / Sold Out
//        TicketCard(
//            title = "Early Bird Special",
//            price = "$40.00",
//            features = listOf("Access to main event hall", "Sold Out"),
//            enabled = false,
//            // The button can still be passed, but the card's content color indicates the disabled state
//            buttonContent = { AddButton(onClick = {}) }
//        )
    }
}

//@Composable
//fun SoldButton() {
//    Button(
//        onClick = { },
//        enabled = false,
//        shape = RoundedCornerShape(8.dp),
//        colors = ButtonDefaults.buttonColors(
//            disabledContainerColor = disabledButtonBackground,
//            disabledContentColor = disabledTextGray.copy(alpha = 0.8f)
//        ),
//        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
//    ) {
//        Text("Sold")
//    }
//}

@Preview(showBackground = true, backgroundColor = 0xFFF7F7F7, name = "Add Button")
@Composable
private fun AddButtonPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        AddButton(onClick = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF7F7F7, name = "Quantity Selector")
@Composable
private fun QuantitySelectorPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        QuantitySelector(quantity = 3, onQuantityChange = {})
    }
}