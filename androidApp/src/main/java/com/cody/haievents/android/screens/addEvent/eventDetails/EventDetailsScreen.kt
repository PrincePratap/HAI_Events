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

// --- Main Screen Composable ---

@Composable
fun EventDetailsScreen() {
    // Colors derived from the image
    val goldColor = Color(0xFFC7A441)
    val lightGoldBorder = Color(0xFFD3B878)
    val screenBackgroundColor = Color(0xFF121212) // A dark background

    // State for the form fields
    var eventTitle by remember { mutableStateOf("") }
    var organiserName by remember { mutableStateOf("") }
    var contactEmail by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var eventTime by remember { mutableStateOf("") }
    var eventDescription by remember { mutableStateOf("") }

    Scaffold(
        containerColor = screenBackgroundColor,
        topBar = { AddEventTopBar(backgroundColor = goldColor) }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            EventCreationStepper()
            Spacer(modifier = Modifier.height(24.dp))

            // Form sections
            EventInputSection(label = "Event Title") {
                EventTextField(
                    value = eventTitle,
                    onValueChange = { eventTitle = it },
                    placeholder = "E.g Music Fiesta 2025",
                    borderColor = lightGoldBorder
                )
            }
            EventInputSection(label = "Organiser Name") {
                EventTextField(
                    value = organiserName,
                    onValueChange = { organiserName = it },
                    placeholder = "E.g Rajesh Singh",
                    borderColor = lightGoldBorder
                )
            }
            EventInputSection(label = "Contact Email") {
                EventTextField(
                    value = contactEmail,
                    onValueChange = { contactEmail = it },
                    placeholder = "e.g rajeshshr@gmail.com",
                    borderColor = lightGoldBorder
                )
            }
            EventInputSection(label = "Event Location") {
                EventTextField(
                    value = eventLocation,
                    onValueChange = { eventLocation = it },
                    placeholder = "E.g Mumbai, India",
                    borderColor = lightGoldBorder
                )
            }

            // Date and Time in a Row
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    EventInputSection(label = "Event Date") {
                        EventTextField(
                            value = eventDate,
                            onValueChange = { eventDate = it },
                            placeholder = "dd-mm-yyyy",
                            borderColor = lightGoldBorder
                        )
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    EventInputSection(label = "Event Time") {
                        EventTextField(
                            value = eventTime,
                            onValueChange = { eventTime = it },
                            placeholder = "Eg. 6:30 PM - 9:30 PM",
                            borderColor = lightGoldBorder
                        )
                    }
                }
            }

            EventInputSection(label = "Event Category") {
                EventCategoryDropdown(borderColor = lightGoldBorder)
            }
            EventInputSection(label = "Event Description") {
                EventTextField(
                    value = eventDescription,
                    onValueChange = { eventDescription = it },
                    placeholder = "Tell us more about your events...",
                    minLines = 4,
                    borderColor = lightGoldBorder
                )
            }
            EventInputSection(label = "Event Poster") {
                EventPosterPicker(borderColor = lightGoldBorder)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = goldColor)
            ) {
                Text("Next", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}


// --- Reusable UI Components ---

@Composable
fun AddEventTopBar(backgroundColor: Color) {
    Surface(
        color = backgroundColor,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle back navigation */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                text = "Add Your Event",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun EventCreationStepper() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Step(text = "Event\nDetails", isCompleted = true, isActive = true)
        HorizontalDivider(modifier = Modifier.weight(1f).padding(horizontal = 8.dp), color = Color.LightGray)
        Step(text = "Bank Transfer\nDetails", isCompleted = true, isActive = false)
    }
}

@Composable
fun Step(text: String, isCompleted: Boolean, isActive: Boolean) {
    val circleColor = if (isActive) Color.DarkGray else Color.LightGray
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(50))
                .background(circleColor),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text, textAlign = TextAlign.Center, fontSize = 12.sp, lineHeight = 14.sp)
    }
}

@Composable
fun EventInputSection(label: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
fun EventTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    borderColor: Color,
    minLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor.copy(alpha = 0.7f),
            cursorColor = borderColor,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        minLines = minLines,
        maxLines = if (minLines > 1) minLines + 2 else 1
    )
}

@Composable
fun EventCategoryDropdown(borderColor: Color) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, borderColor.copy(alpha = 0.7f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("-Select category-", color = Color.Gray)
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Dropdown Arrow",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun EventPosterPicker(borderColor: Color) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, borderColor.copy(alpha = 0.7f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp, topEnd = 0.dp, bottomEnd = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE0E0E0),
                    contentColor = Color.Black.copy(alpha = 0.8f)
                ),
                modifier = Modifier.height(55.dp)
            ) {
                Text("Choose File", fontWeight = FontWeight.Normal)
            }
            Text(
                text = "No file chosen",
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}


// --- Preview Function ---

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Add Event Screen")
@Composable
fun AddEventScreenPreview() {
    MaterialTheme {
        EventDetailsScreen()
    }
}