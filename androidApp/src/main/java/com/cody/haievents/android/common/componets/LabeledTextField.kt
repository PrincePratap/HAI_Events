package com.cody.haievents.android.common.componets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.theming.goldColor

import androidx.compose.foundation.clickable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalFocusManager
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


/**
 * Reusable labeled text field with customization for keyboard type,
 * capitalization, and line count.
 */
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    imeAction: ImeAction = ImeAction.Next,
    singleLine: Boolean = true,
    maxLines: Int = 1,
) {
    val resolvedMaxLines = if (singleLine) 1 else maxOf(1, maxLines)

    Column(modifier = modifier) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = goldColor,
                unfocusedBorderColor = goldColor.copy(alpha = 0.7f),
                cursorColor = goldColor,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = singleLine,
            maxLines = resolvedMaxLines,
            keyboardOptions = KeyboardOptions(
                capitalization = capitalization,
                keyboardType = keyboardType,
                imeAction = imeAction
            )
        )
    }
}





@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabeledDatePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "YYYY-MM-DD",
    // default output format: 1999-08-15
    dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"),
    initialSelectedDateMillis: Long? = null,
    minDateMillis: Long? = null,
    maxDateMillis: Long? = null
) {
    val focusManager = LocalFocusManager.current
    var openDialog by remember { mutableStateOf(false) }

    // Restrict selectable dates using SelectableDates (correct API)
    val selectableDates = remember(minDateMillis, maxDateMillis) {
        object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val afterMin = minDateMillis?.let { utcTimeMillis >= it } ?: true
                val beforeMax = maxDateMillis?.let { utcTimeMillis <= it } ?: true
                return afterMin && beforeMax
            }
            override fun isSelectableYear(year: Int): Boolean = true
        }
    }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDateMillis,
        selectableDates = selectableDates
    )

    if (openDialog) {
        DatePickerDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val text = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .format(dateFormatter)          // -> "1999-08-15"
                        onValueChange(text)
                    }
                    openDialog = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Column(modifier = modifier) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = {}, // read-only; value set via dialog
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    focusManager.clearFocus()
                    openDialog = true
                },
            readOnly = true,
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            trailingIcon = {
                Icon(Icons.Outlined.CalendarMonth, contentDescription = "Pick date")
            },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = goldColor,
                unfocusedBorderColor = goldColor.copy(alpha = 0.7f),
                cursorColor = goldColor,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true,
            maxLines = 1
        )
    }
}


