package com.cody.haievents.android.common.componets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.Show.model.Role
import com.cody.haievents.android.common.theming.darkTextColor
import com.cody.haievents.android.common.theming.goldColor

// ---- Ticket UI Row (strings for easy typing; convert to Int on submit) ----
data class TicketRowUi(
    val role: Role = Role.PERFORMER,
    val name: String = "",
    val quantity: String = "",
    val price: String = ""
)

@Composable
fun TicketDetailsSection(
    tickets: List<TicketRowUi> = emptyList(),
    onTicketChange: (index: Int, updated: TicketRowUi) -> Unit,
    onAddTicket: () -> Unit,
    onRemoveTicket: (index: Int) -> Unit,
    colors: TextFieldColors
) {
    val fieldShape = RoundedCornerShape(12.dp)

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            "Tickets",
            style = MaterialTheme.typography.titleMedium,
            color = darkTextColor,
            fontWeight = FontWeight.Bold
        )

        // Responsive header (show only on wide screens)
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val isCompact = maxWidth < 520.dp

            if (!isCompact) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Ticket Type", modifier = Modifier.weight(1.2f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
                    Text("Quantity",    modifier = Modifier.weight(0.9f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
                    Text("Price",       modifier = Modifier.weight(0.9f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.width(40.dp))
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                tickets.forEachIndexed { index, item ->
                    if (isCompact) {
                        // STACKED (phones / narrow widths)
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                OutlinedTextField(
                                    value = item.name,
                                    onValueChange = { onTicketChange(index, item.copy(name = it)) },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = fieldShape,
                                    colors = colors,
                                    label = { Text("Ticket Type") },
                                    placeholder = { Text("e.g., VIP") },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                                )

                                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                    OutlinedTextField(
                                        value = item.quantity,
                                        onValueChange = { t ->
                                            onTicketChange(index, item.copy(quantity = t.filter(Char::isDigit)))
                                        },
                                        modifier = Modifier.weight(1f),
                                        shape = fieldShape,
                                        colors = colors,
                                        label = { Text("Quantity") },
                                        placeholder = { Text("e.g., 50") },
                                        singleLine = true,
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Next
                                        )
                                    )

                                    OutlinedTextField(
                                        value = item.price,
                                        onValueChange = { t ->
                                            onTicketChange(index, item.copy(price = t.filter(Char::isDigit)))
                                        },
                                        modifier = Modifier.weight(1f),
                                        shape = fieldShape,
                                        colors = colors,
                                        label = { Text("Price (₹)") },
                                        placeholder = { Text("e.g., 999") },
                                        singleLine = true,
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Done
                                        )
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    IconButton(
                                        onClick = { onRemoveTicket(index) },
                                        enabled = tickets.size > 1
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Remove",
                                            tint = if (tickets.size > 1) Color(0xFFD32F2F) else Color.LightGray
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        // WIDE (table-like)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = item.name,
                                onValueChange = { onTicketChange(index, item.copy(name = it)) },
                                modifier = Modifier.weight(1.2f),
                                shape = fieldShape,
                                colors = colors,
                                placeholder = { Text("e.g., VIP") },
                                singleLine = true,
                                label = { Text("Ticket Type") },
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                            )

                            OutlinedTextField(
                                value = item.quantity,
                                onValueChange = { t ->
                                    onTicketChange(index, item.copy(quantity = t.filter(Char::isDigit)))
                                },
                                modifier = Modifier.weight(0.9f),
                                shape = fieldShape,
                                colors = colors,
                                placeholder = { Text("e.g., 50") },
                                singleLine = true,
                                label = { Text("Quantity") },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                )
                            )

                            OutlinedTextField(
                                value = item.price,
                                onValueChange = { t ->
                                    onTicketChange(index, item.copy(price = t.filter(Char::isDigit)))
                                },
                                modifier = Modifier.weight(0.9f),
                                shape = fieldShape,
                                colors = colors,
                                placeholder = { Text("e.g., 999") },
                                singleLine = true,
                                label = { Text("Price (₹)") },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                )
                            )

                            IconButton(onClick = { onRemoveTicket(index) }, enabled = tickets.size > 1) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove",
                                    tint = if (tickets.size > 1) Color(0xFFD32F2F) else Color.LightGray
                                )
                            }
                        }
                    }
                }
            }
        }

        // Add new row
        FilledTonalButton(
            onClick = onAddTicket,
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Ticket Type", tint = goldColor)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Add Ticket Type", color = goldColor, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun RoleSelector(
    value: Role,
    onChange: (Role) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip(
            selected = value == Role.PERFORMER,
            onClick = { onChange(Role.PERFORMER) },
            label = { Text("Performer") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = goldColor.copy(alpha = 0.15f),
                selectedLabelColor = darkTextColor
            )
        )
        FilterChip(
            selected = value == Role.ATTENDEE,
            onClick = { onChange(Role.ATTENDEE) },
            label = { Text("Attendee") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = goldColor.copy(alpha = 0.15f),
                selectedLabelColor = darkTextColor
            )
        )
    }
}

@Preview(showBackground = true, name = "TicketDetailsSection – Compact")
@Composable
private fun TicketDetailsSectionPreviewCompact() {
    MaterialTheme {
        Surface(Modifier.fillMaxWidth().padding(16.dp)) {
            var tickets by remember {
                mutableStateOf(
                    listOf(
                        TicketRowUi(role = Role.PERFORMER, name = "Performer", quantity = "10", price = "299"),
                        TicketRowUi(role = Role.ATTENDEE,  name = "Audience",  quantity = "50", price = "99")
                    )
                )
            }
            TicketDetailsSection(
                tickets = tickets,
                onTicketChange = { index, updated ->
                    tickets = tickets.toMutableList().also { it[index] = updated }
                },
                onAddTicket = { tickets = tickets + TicketRowUi() },
                onRemoveTicket = { index ->
                    if (tickets.size > 1) tickets = tickets.toMutableList().also { it.removeAt(index) }
                },
                colors = TextFieldDefaults.colors()
            )
        }
    }
}
