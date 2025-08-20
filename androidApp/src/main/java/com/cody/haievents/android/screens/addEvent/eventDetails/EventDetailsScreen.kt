package com.cody.haievents.android.screens.addEvent.eventDetails





import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle


// Define colors to match the screenshot
val goldColor = Color(0xFFC9A959)
val lightGoldBorder = Color(0xFFE0D0B1)
val darkTextColor = Color.Black
val lightTextColor = Color.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen() {
    var eventTitle by remember { mutableStateOf("") }
    var organiserName by remember { mutableStateOf("") }
    var contactEmail by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var eventTime by remember { mutableStateOf("") }
    var eventDescription by remember { mutableStateOf("") }
    var ticketType by remember { mutableStateOf("Gold") }
    var ticketQuantity by remember { mutableStateOf("500") }
    var ticketPrice by remember { mutableStateOf("₹300") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Your Event", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back press */ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = goldColor
                )
            )
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
                eventTitle = eventTitle,
                onEventTitleChange = { eventTitle = it },
                organiserName = organiserName,
                onOrganiserNameChange = { organiserName = it },
                contactEmail = contactEmail,
                onContactEmailChange = { contactEmail = it },
                eventLocation = eventLocation,
                onEventLocationChange = { eventLocation = it },
                eventDate = eventDate,
                onEventDateChange = { eventDate = it },
                eventTime = eventTime,
                onEventTimeChange = { eventTime = it },
                eventDescription = eventDescription,
                onEventDescriptionChange = { eventDescription = it },
                ticketType = ticketType,
                onTicketTypeChange = { ticketType = it },
                ticketQuantity = ticketQuantity,
                onTicketQuantityChange = { ticketQuantity = it },
                ticketPrice = ticketPrice,
                onTicketPriceChange = { ticketPrice = it }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* Handle Next */ },
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
    ticketType: String, onTicketTypeChange: (String) -> Unit,
    ticketQuantity: String, onTicketQuantityChange: (String) -> Unit,
    ticketPrice: String, onTicketPriceChange: (String) -> Unit,
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
                placeholder = "dd-mm-yyyy",
                modifier = Modifier.weight(1f),
                colors = textFieldColors
            )
            FormInput(
                label = "Event Time",
                value = eventTime,
                onValueChange = onEventTimeChange,
                placeholder = "Eg. 6:30 PM - 9:30 PM",
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
        EventPosterUploader()
        TicketDetailsSection(
            ticketType = ticketType, onTicketTypeChange = onTicketTypeChange,
            ticketQuantity = ticketQuantity, onTicketQuantityChange = onTicketQuantityChange,
            ticketPrice = ticketPrice, onTicketPriceChange = onTicketPriceChange,
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
fun EventPosterUploader() {
    Column {
        Text("Event Poster", fontWeight = FontWeight.Bold, color = darkTextColor)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "No file chosen",
            onValueChange = {},
            readOnly = true,
            leadingIcon = {
                Button(
                    onClick = { /* Handle file choose */ },
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

@Composable
fun TicketDetailsSection(
    ticketType: String, onTicketTypeChange: (String) -> Unit,
    ticketQuantity: String, onTicketQuantityChange: (String) -> Unit,
    ticketPrice: String, onTicketPriceChange: (String) -> Unit,
    colors: TextFieldColors
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Tickets Details", fontWeight = FontWeight.Bold, color = darkTextColor)
        Row {
            Text("Ticket Type", modifier = Modifier.weight(1f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
            Text("Quantity", modifier = Modifier.weight(1f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
            Text("Price", modifier = Modifier.weight(1f), color = darkTextColor, fontWeight = FontWeight.SemiBold)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = ticketType, onValueChange = onTicketTypeChange,
                modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), colors = colors
            )
            OutlinedTextField(
                value = ticketQuantity, onValueChange = onTicketQuantityChange,
                modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), colors = colors
            )
            OutlinedTextField(
                value = ticketPrice, onValueChange = onTicketPriceChange,
                modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), colors = colors
            )
        }
        TextButton(onClick = { /* Add another ticket type */ }) {
            Icon(Icons.Default.Add, contentDescription = "Add Ticket Type", tint = goldColor)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Add Another Ticket Type", color = goldColor, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun AddEventScreenPreview() {

        EventDetailsScreen()

}