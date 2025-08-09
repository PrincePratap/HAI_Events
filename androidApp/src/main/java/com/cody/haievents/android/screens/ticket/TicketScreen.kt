package com.cody.haievents.android.screens.ticket




import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class to hold the information for each pass
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material3.HorizontalDivider

import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush


// Define colors to match the image
private val goldColor = Color(0xFFC9A24C)
private val lightGoldColor = Color(0xFFE6C47C)
private val screenBackground = Color(0xFFF7F7F7)
private val disabledTextGray = Color(0xFFBDBDBD)
private val disabledButtonBackground = Color(0xFFE0E0E0)
private val primaryTextColor = Color(0xFF333333)
private val dividerColor = Color.Black.copy(alpha = 0.08f)

@Composable
fun TicketScreen() {
    // State is initialized to match the provided image
    var goldPassCount by remember { mutableIntStateOf(1) }
    var platinumPassCount by remember { mutableIntStateOf(0) }

    // Calculate total based on state
    val totalTickets = goldPassCount + platinumPassCount
    val totalPrice = (goldPassCount * 999) + (platinumPassCount * 1499)

    Scaffold(
        bottomBar = {
            // The bottom bar is only shown if at least one ticket is selected
            if (totalTickets > 0) {
                BottomCheckoutBar(totalPrice = totalPrice, ticketCount = totalTickets)
            }
        },
        containerColor = screenBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            // Platinum Pass
            PassItem(
                title = "Platinum Pass",
                price = "₹1499",
                features = listOf(
                    "Backstage meet & greet with artists",
                    "Exclusive merchandise (if available)",
                    "Dedicated support / help desk"
                ),
                buttonContent = {
                    if (platinumPassCount == 0) {
                        AddButton { platinumPassCount = 1 }
                    } else {
                        QuantitySelector(
                            quantity = platinumPassCount,
                            onQuantityChange = { platinumPassCount = it }
                        )
                    }
                }
            )

            HorizontalDivider(color = dividerColor, thickness = 1.dp)

            // Gold Pass
            PassItem(
                title = "Gold Pass",
                price = "₹999",
                features = listOf(
                    "Backstage meet & greet with artists",
                    "Exclusive merchandise (if available)"
                ),
                buttonContent = {
                    if (goldPassCount == 0) {
                        AddButton { goldPassCount = 1 }
                    } else {
                        QuantitySelector(
                            quantity = goldPassCount,
                            onQuantityChange = { goldPassCount = it }
                        )
                    }
                }
            )

            HorizontalDivider(color = dividerColor, thickness = 1.dp)

            // Silver Pass
            PassItem(
                title = "Silver Pass",
                price = "₹699",
                features = listOf(
                    "Backstage meet & greet with artists",
                    "Exclusive merchandise (if available)"
                ),
                enabled = false,
                buttonContent = { SoldButton() }
            )
        }
    }
}

@Composable
fun PassItem(
    title: String,
    price: String,
    features: List<String>,
    enabled: Boolean = true,
    buttonContent: @Composable () -> Unit
) {
    val contentColor = if (enabled) primaryTextColor else disabledTextGray

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(screenBackground)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = contentColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = price,
                fontSize = 18.sp,
                color = contentColor
            )
            Spacer(modifier = Modifier.height(12.dp))
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
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier.padding(top = 4.dp)) {
            buttonContent()
        }
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

@Composable
fun SoldButton() {
    Button(
        onClick = { },
        enabled = false,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = disabledButtonBackground,
            disabledContentColor = disabledTextGray.copy(alpha = 0.8f)
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
    ) {
        Text("Sold")
    }
}

@Composable
fun BottomCheckoutBar(totalPrice: Int, ticketCount: Int) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "₹ $totalPrice",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = primaryTextColor
                )
                Text(
                    text = if (ticketCount == 1) "1 Ticket" else "$ticketCount Tickets",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Button(
                onClick = { /* Handle get ticket action */ },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(50.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(lightGoldColor, goldColor)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(horizontal = 32.dp)
            ) {
                Text(
                    "Get Ticket",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketPurchaseScreenPreview() {
        TicketScreen()
}