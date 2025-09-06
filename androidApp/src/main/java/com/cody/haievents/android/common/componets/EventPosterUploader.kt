package com.cody.haievents.android.common.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.theming.darkTextColor
import com.cody.haievents.android.common.theming.lightGoldBorder
import com.cody.haievents.android.common.theming.lightTextColor

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