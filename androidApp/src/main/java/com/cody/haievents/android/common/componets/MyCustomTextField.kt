package com.cody.haievents.android.common.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, placeholderText: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholderText,
//                color = TextSecondaryColor
            )
        },
        shape = RoundedCornerShape(16.dp),
        // --- START: FIX ---
//        colors = TextFieldDefaults.colors(
//            focusedContainerColor = TextFieldBackgroundColor, // M3 uses focused/unfocused variants
//            unfocusedContainerColor = TextFieldBackgroundColor,
//            unfocusedBorderColor = TextFieldBorderColor,
//            focusedBorderColor = ButtonColor,
//            focusedTextColor = TextPrimaryColor, // Use focused/unfocused text color
//            unfocusedTextColor = TextPrimaryColor,
//            cursorColor = ButtonColor,
//            disabledContainerColor = TextFieldBackgroundColor // Good practice to set disabled state too
//        ),
        // --- END: FIX ---
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp)
    )
}