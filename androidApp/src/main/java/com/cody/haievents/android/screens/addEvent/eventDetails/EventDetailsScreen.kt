package com.cody.haievents.android.screens.addEvent.eventDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.Show.model.Role
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.android.common.theming.darkTextColor
import com.cody.haievents.android.common.theming.goldColor
import com.cody.haievents.android.common.theming.lightGoldBorder
import com.cody.haievents.android.common.theming.lightTextColor


// ---- Ticket UI Row (strings for easy typing; convert to Int on submit) ----
data class TicketRowUi(
    val role: Role = Role.ATTENDEE,  // default to attendee
    val name: String = "",
    val quantity: String = "",
    val price: String = ""
)

@Composable
fun EventDetailsScreen(
    uiState: EventDetailsUiState = EventDetailsUiState(),
    onNextClick: (List<TicketTypeRequest>) -> Unit = {},
    onBackClick: () -> Unit = {},
    onChangeTitle: (String) -> Unit = {},
    onChangeOrganiserName: (String) -> Unit = {},
    onChangeContactEmail: (String) -> Unit = {},
    onChangeEventLocation: (String) -> Unit = {},
    onChangeEventDate: (String) -> Unit = {},
    onChangeEventTime: (String) -> Unit = {},
    onChangeEventDescription: (String) -> Unit = {},
    clickOnChooseFile: () -> Unit = {},
) {
    // 🔹 Dynamic list of ticket rows
    val tickets = remember { mutableStateListOf(TicketRowUi()) }

    Scaffold(
        topBar = {
            // Your top bar, if any
            // SmallTopAppBar(title = { Text("Create Event") }, navigationIcon = { ... })
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            EventProgressIndicator()
            Spacer(modifier = Modifier.height(24.dp))

            EventForm(
                eventTitle = uiState.eventTitle,
                onEventTitleChange = onChangeTitle,
                organiserName = uiState.organiserName,
                onOrganiserNameChange = onChangeOrganiserName,
                contactEmail = uiState.contactEmail,
                onContactEmailChange = onChangeContactEmail,
                eventLocation = uiState.eventLocation,
                onEventLocationChange = onChangeEventLocation,
                eventDate = uiState.eventDate,
                onEventDateChange = onChangeEventDate,
                eventTime = uiState.eventTime,
                onEventTimeChange = onChangeEventTime,
                eventDescription = uiState.eventDescription,
                onEventDescriptionChange = onChangeEventDescription,

                // 🔹 Tickets (dynamic)
                tickets = tickets,
                onTicketChange = { idx, updated -> tickets[idx] = updated },
                onAddTicket = { tickets.add(TicketRowUi()) },
                onRemoveTicket = { idx -> if (tickets.size > 1) tickets.removeAt(idx) },

                clickOnChooseFile = clickOnChooseFile
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // 🔹 Build List<TicketTypeRequest> for API
                    val built: List<TicketTypeRequest> = tickets.map {
                        TicketTypeRequest(
                            name = it.name.trim(),
                            price = it.price.toIntOrNull() ?: 0,
                            role = it.role,
                            quantity = it.quantity.toIntOrNull() ?: 0
                        )
                    }
                    onNextClick(built)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = goldColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Next", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun EventProgressIndicator() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Step(title = "Event\nDetails", isActive = true)
        Divider(
            modifier = Modifier.weight(1f),
            color = Color.LightGray,
            thickness = 1.dp
        )
        Step(title = "Bank Transfer\nDetails", isActive = false)
    }
}

@Composable
fun Step(title: String, isActive: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(120.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = if (isActive) darkTextColor else lightTextColor,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = title,
            tint = if (isActive) darkTextColor else Color.LightGray,
            modifier = Modifier.size(28.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventForm(
    eventTitle: String, onEventTitleChange: (String) -> Unit,
    organiserName: String, onOrganiserNameChange: (String) -> Unit,
    contactEmail: String, onContactEmailChange: (String) -> Unit,
    eventLocation: String, onEventLocationChange: (String) -> Unit,
    eventDate: String, onEventDateChange: (String) -> Unit,
    eventTime: String, onEventTimeChange: (String) -> Unit,
    eventDescription: String, onEventDescriptionChange: (String) -> Unit,

    // 🔹 Dynamic tickets API
    tickets: List<TicketRowUi>,
    onTicketChange: (index: Int, updated: TicketRowUi) -> Unit,
    onAddTicket: () -> Unit,
    onRemoveTicket: (index: Int) -> Unit,

    clickOnChooseFile: () -> Unit
) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = goldColor,
        unfocusedBorderColor = lightGoldBorder,
        cursorColor = goldColor,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
    )

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        FormInput(
            label = "Event Title",
            value = eventTitle,
            onValueChange = onEventTitleChange,
            placeholder = "E.g Music Fiesta 2025",
            colors = textFieldColors
        )
        FormInput(
            label = "Organiser Name",
            value = organiserName,
            onValueChange = onOrganiserNameChange,
            placeholder = "E.g Rajesh Singh",
            colors = textFieldColors
        )
        FormInput(
            label = "Contact Email",
            value = contactEmail,
            onValueChange = onContactEmailChange,
            placeholder = "E.g rajeshshr@gmail.com",
            colors = textFieldColors
        )
        FormInput(
            label = "Event Location",
            value = eventLocation,
            onValueChange = onEventLocationChange,
            placeholder = "E.g Mumbai, India",
            colors = textFieldColors
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FormInput(
                label = "Event Date",
                value = eventDate,
                onValueChange = onEventDateChange,
                placeholder = "yyyy-mm-dd",
                modifier = Modifier.weight(1f),
                colors = textFieldColors
            )
            FormInput(
                label = "Event Time",
                value = eventTime,
                onValueChange = onEventTimeChange,
                placeholder = "HH:mm or 6:30 PM",
                modifier = Modifier.weight(1f),
                colors = textFieldColors
            )
        }

        CategoryDropdown(colors = textFieldColors)

        FormInput(
            label = "Event Description",
            value = eventDescription,
            onValueChange = onEventDescriptionChange,
            placeholder = "Tell us more about your events...",
            singleLine = false,
            modifier = Modifier.height(120.dp),
            colors = textFieldColors
        )

        EventPosterUploader(clickOnChooseFile = clickOnChooseFile)

        // 🔹 Tickets section
        TicketDetailsSection(
            tickets = tickets,
            onTicketChange = onTicketChange,
            onAddTicket = onAddTicket,
            onRemoveTicket = onRemoveTicket,
            colors = textFieldColors
        )
    }
}

@Composable
fun FormInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    colors: TextFieldColors
) {
    Column(modifier = modifier) {
        Text(label, fontWeight = FontWeight.Bold, color = darkTextColor)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = lightTextColor) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = singleLine,
            colors = colors
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(colors: TextFieldColors) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }
    val categories = listOf("Music", "Sports", "Conference", "Workshop", "Art")

    Column {
        Text("Event Category", fontWeight = FontWeight.Bold, color = darkTextColor)
        Spacer(modifier = Modifier.height(8.dp))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("-Select category-", color = lightTextColor) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = RoundedCornerShape(12.dp),
                colors = colors
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EventPosterUploader(
    clickOnChooseFile: () -> Unit = {}
) {
    Column {
        Text("Event Poster", fontWeight = FontWeight.Bold, color = darkTextColor)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "No file chosen",
            onValueChange = {},
            readOnly = true,
            leadingIcon = {
                Button(
                    onClick = clickOnChooseFile,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = darkTextColor
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Text("Choose File", fontWeight = FontWeight.Normal)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = lightGoldBorder,
                unfocusedBorderColor = lightGoldBorder,
                disabledTextColor = lightTextColor,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            enabled = false
        )
    }
}

// -------------------------- Tickets Section --------------------------

@Composable
fun TicketDetailsSection(
    tickets: List<TicketRowUi>,
    onTicketChange: (index: Int, updated: TicketRowUi) -> Unit,
    onAddTicket: () -> Unit,
    onRemoveTicket: (index: Int) -> Unit,
    colors: TextFieldColors
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Tickets Details", fontWeight = FontWeight.Bold, color = darkTextColor)

        // Header
        Row {
            Text("Role",        modifier = Modifier.weight(1f),   color = darkTextColor, fontWeight = FontWeight.SemiBold)
            Text("Ticket Type", modifier = Modifier.weight(1.2f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
            Text("Quantity",    modifier = Modifier.weight(0.9f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
            Text("Price",       modifier = Modifier.weight(0.9f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.width(40.dp))
        }

        // Rows
        tickets.forEachIndexed { index, item ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RoleSelector(
                    value = item.role,
                    onChange = { onTicketChange(index, item.copy(role = it)) },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = item.name,
                    onValueChange = { onTicketChange(index, item.copy(name = it)) },
                    modifier = Modifier.weight(1.2f),
                    shape = RoundedCornerShape(12.dp),
                    colors = colors,
                    placeholder = { Text("E.g. VIP") }
                )

                OutlinedTextField(
                    value = item.quantity,
                    onValueChange = { t ->
                        onTicketChange(index, item.copy(quantity = t.filter(Char::isDigit)))
                    },
                    modifier = Modifier.weight(0.9f),
                    shape = RoundedCornerShape(12.dp),
                    colors = colors,
                    placeholder = { Text("E.g. 50") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = item.price,
                    onValueChange = { t ->
                        onTicketChange(index, item.copy(price = t.filter(Char::isDigit)))
                    },
                    modifier = Modifier.weight(0.9f),
                    shape = RoundedCornerShape(12.dp),
                    colors = colors,
                    placeholder = { Text("E.g. 999") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                IconButton(onClick = { onRemoveTicket(index) }, enabled = tickets.size > 1) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = if (tickets.size > 1) Color.Red else Color.LightGray
                    )
                }
            }
        }

        // Add new row
        TextButton(onClick = onAddTicket) {
            Icon(Icons.Default.Add, contentDescription = "Add Ticket Type", tint = goldColor)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Add Another Ticket Type", color = goldColor, fontWeight = FontWeight.Bold)
        }
    }
}

// Two-option selector for Role (Performer / Attendee)
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

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun AddEventScreenPreview() {
    EventDetailsScreen()
}
