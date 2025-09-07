package com.cody.haievents.android.common.componets.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ChairSelectionDialog(
    onDismiss: () -> Unit,
    onSelectCushion: () -> Unit,
    onSelectPlastic: () -> Unit

) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Select Chair Type",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onSelectCushion() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cushion Chairs")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { onSelectPlastic() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Plastic Chairs")
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewChairSelectionDialog() {
//    MaterialTheme {

//    }
}