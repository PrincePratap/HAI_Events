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
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.CommonButton
import com.cody.haievents.android.common.componets.EventPosterUploader
import com.cody.haievents.android.common.componets.TicketDetailsSection
import com.cody.haievents.android.common.componets.TicketRowUi
import com.cody.haievents.android.common.theming.darkTextColor
import com.cody.haievents.android.common.theming.goldColor
import com.cody.haievents.android.common.theming.lightGoldBorder
import com.cody.haievents.android.common.theming.lightTextColor




@Composable
fun EventDetailsScreen(
    uiState: EventDetailsUiState = EventDetailsUiState(),
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onChangeTitle: (String) -> Unit = {},
    onChangeOrganiserName: (String) -> Unit = {},
    onChangeContactEmail: (String) -> Unit = {},
    onChangeEventLocation: (String) -> Unit = {},
    onChangeEventDate: (String) -> Unit = {},
    onChangeEventTime: (String) -> Unit = {},
    onChangeEventDescription: (String) -> Unit = {},
) {
    // 🔹 Dynamic list of ticket rows

    Scaffold(
        topBar = {
           CommonTopBar(onBackClick = onBackClick, title = "Event Details", modifier = Modifier)
        },
        bottomBar = {
            CommonButton(text = "Next", onClick = onNextClick)
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

            )

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






@Preview(showBackground = true,)
@Composable
fun AddEventScreenPreview() {
    EventDetailsScreen()
}
