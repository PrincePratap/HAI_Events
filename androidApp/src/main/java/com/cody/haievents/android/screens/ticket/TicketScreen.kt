package com.cody.haievents.android.screens.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.componets.card.AddButton
import com.cody.haievents.android.common.componets.card.QuantitySelector
import com.cody.haievents.android.common.componets.card.TicketCard
import com.cody.haievents.android.common.theming.goldColor
import com.cody.haievents.android.common.theming.lightGoldColor
import com.cody.haievents.android.common.theming.primaryTextColor
import com.cody.haievents.android.common.theming.screenBackground
import com.cody.haievents.Show.data.TicketType

@Composable
fun TicketScreen(
    uiState: TicketUiState,
    onQuantityChange: (ticketId: String, newQuantity: Int) -> Unit,
    onGetTicketClick: () -> Unit,
) {
    Scaffold(
        bottomBar = {
            if (uiState.totalTickets > 0) {
                BottomCheckoutBar(
                    totalPrice = uiState.totalPrice,
                    ticketCount = uiState.totalTickets,
                    onGetTicketClick = onGetTicketClick
                )
            }
        },
        containerColor = screenBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(Modifier.height(8.dp)) }

            items(items = uiState.ticketList, key = { it.id }) { ticket ->
                val selectedQty = uiState.selectedQuantities[ticket.id] ?: 0

                TicketCard(
                    title = ticket.name,
                    price = "₹${ticket.price}", // Display price as received
                    features = listOf(
                        "Backstage meet & greet with artists",
                        "Exclusive merchandise (if available)",
                        "Dedicated support / help desk"
                    ),
                    buttonContent = {
                        if (selectedQty == 0) {
                            AddButton {
                                onQuantityChange(ticket.id.toString(), 1)
                            }
                        } else {
                            QuantitySelector(
                                quantity = selectedQty,
                                onQuantityChange = { newQuantity ->
                                    onQuantityChange(ticket.id.toString(), newQuantity.coerceAtLeast(0))
                                }
                            )
                        }
                    }
                )
            }

            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}

@Composable
fun BottomCheckoutBar(
    totalPrice: Int,   // in ₹ (whole rupees)
    ticketCount: Int,
    onGetTicketClick: () -> Unit
) {
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
                    text = "₹$totalPrice",
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
                onClick = onGetTicketClick,
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
                Text("Get Ticket", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketPurchaseScreenPreview() {
//    val sampleTickets = listOf(
//        TicketType(id = 101, event_id = 1, event_source = "movie", role_type = "performer", name = "Performer 1", price = "1.00", quantity = 10, created_at = "", updated_at = ""),
//        TicketType(id = 102, event_id = 1, event_source = "movie", role_type = "performer", name = "Performer 2", price = "200.00", quantity = 10, created_at = "", updated_at = "")
//    )
//    val state = TicketUiState(
//        ticketList = sampleTickets,
//        selectedQuantities = mapOf(101 to 0, 102 to 2),
//        totalTickets = 2,
//        totalPrice = 400
//    )

//    TicketScreen(
//        uiState = state,
//        onQuantityChange = { _, _ -> },
//        onGetTicketClick = {}
//    )
}
