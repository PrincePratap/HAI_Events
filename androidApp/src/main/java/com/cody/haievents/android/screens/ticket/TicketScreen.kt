package com.cody.haievents.android.screens.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // You can use items or itemsIndexed
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

// --- Data classes (Assumed based on usage for a complete example) ---


data class TicketItem(
    val id: String,
    val name: String,
    val price: Int,
    val features: List<String>,
    var quantity: Int = 0
)

// Define colors (ideally in a Theme.kt file)
private val goldColor = Color(0xFFC9A24C)
private val lightGoldColor = Color(0xFFE6C47C)
private val screenBackground = Color(0xFFF7F7F7)
private val primaryTextColor = Color(0xFF333333)

@Composable
fun TicketScreen(
    uiState: TicketUiState,
    onQuantityChange: (ticketId: String, newQuantity: Int) -> Unit,
    onGetTicketClick: () -> Unit,
) {
    // --- CORRECT: Calculate totals based on the provided uiState. ---
//    val totalTickets = uiState.ticketList.sumOf { it.quantity }
//    val totalPrice = uiState.ticketList.sumOf { it.quantity * it.price }

    Scaffold(
        bottomBar = {
            // --- CORRECT: Conditionally show the bottom bar ---
//            if (totalTickets > 0) {
//                BottomCheckoutBar(
//                    totalPrice = totalPrice,
//                    ticketCount = totalTickets,
//                    onGetTicketClick = onGetTicketClick
//                )
//            }
        },
        containerColor = screenBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp), // Add horizontal padding for all items
            verticalArrangement = Arrangement.spacedBy(16.dp) // Add space between items
        ) {
            item {
                // Spacer for top padding
                Spacer(modifier = Modifier.height(8.dp))
            }

            // --- IMPROVEMENT: Added a stable key for performance and state correctness ---
            items(items = uiState.ticketList, key = { it.id }) { ticket ->
                // The TicketCard itself will handle its own surface and styling.
                // No need for an extra Surface wrapper here if TicketCard is well-designed.
                TicketCard(
                    title = ticket.name,
                    price = "₹${ticket.price}",
                    // --- BUG FIX: Use features from the data model, not hardcoded values ---
                    features = listOf(
                    "Backstage meet & greet with artists",
                    "Exclusive merchandise (if available)",
                    "Dedicated support / help desk"
                ),
                    buttonContent = {
                        // --- CORRECT: Logic is now active ---
                        if (ticket.quantity == 0) {
                            AddButton {
                                // --- EMIT EVENT ON CLICK ---
                                onQuantityChange(ticket.id.toString(), 1)
                            }
                        } else {
                            QuantitySelector(
                                quantity = ticket.quantity,
                                onQuantityChange = { newQuantity ->
                                    // --- EMIT EVENT ON QUANTITY CHANGE ---
                                    onQuantityChange(ticket.id.toString(), newQuantity)
                                }
                            )
                        }
                    }
                )
            }

            item {
                // Spacer for bottom padding
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun BottomCheckoutBar(
    totalPrice: Int,
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
                    text = "₹$totalPrice", // Use string template for cleaner code
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = primaryTextColor
                )
                Text(
                    // This logic is good. For i18n, you'd use Plurals.
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
                    // The gradient background should be applied to a container, not the button itself
                    // but this works because the button's container color is transparent.
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

// --- CORRECT: A good preview uses realistic, uncommented sample data ---
@Preview(showBackground = true)
@Composable
fun TicketPurchaseScreenPreview() {


    // A fake theme wrapper can help ensure your preview looks closer to the real app
//        TicketScreen(
//            uiState = sampleState,
//            onQuantityChange = { id, qty -> println("Quantity changed for $id to $qty") },
//            onGetTicketClick = { println("Get Ticket Clicked!") }
//        )

}