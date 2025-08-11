package com.cody.haievents.android.common.componets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.R

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.filled.Clear

import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle


val mutedBrown = Color(0xFF8D6E63)
val lightText = Color(0xFF676767)

@Composable
fun SearchBarCommonNonClickable(
    value: String = "",
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()

            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        tonalElevation = 2.dp,    // subtle surface tint
        shadowElevation = 2.dp    // actual drop shadow
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(12.dp),     // inner padding
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = mutedBrown
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = if (value.isEmpty()) "Search for order" else value,
                color = if (value.isEmpty()) lightText else Color.Black,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarCommonPreview() {

    SearchBarCommonNonClickable(
        value = "Search for Shows, events, artists...",
        onClick = {  }
    )
}



@Composable
fun ShowsSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "Search for shows..."
) {
    val goldColor = Color(0xFFDDB95E)
    val cornerRadius = 16.dp
    val mutedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                width = 1.5.dp,
                color = goldColor,
                shape = RoundedCornerShape(cornerRadius)
            ),
        shape = RoundedCornerShape(cornerRadius),
        color = MaterialTheme.colorScheme.surface
    ) {
        // We use BasicTextField for full control over styling, removing Material's default decorations
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxSize(),
            singleLine = true,
            // Set the text color and style for the input
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            ),
            // Use a SolidColor brush for the cursor
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Search Icon
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search), // Ensure you have this drawable
                        contentDescription = "Search Icon",
                        tint = mutedTextColor
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // This box handles the placeholder and the actual text input
                    Box(modifier = Modifier.weight(1f)) {
                        if (query.isEmpty()) {
                            Text(
                                text = placeholderText,
                                color = mutedTextColor,
                                fontSize = 18.sp
                            )
                        }
                        // This is where the user's typed text will be rendered
                        innerTextField()
                    }

                    // Show a clear button only if the query is not empty
                    if (query.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Search",
                            tint = mutedTextColor,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { onQueryChange("") } // Clears the text
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowsSearchBarPreview() {
    // Use a mutable state for the preview to test interactions
    var query by remember { mutableStateOf("") }
    ShowsSearchBar(query = query, onQueryChange = { query = it })
}

@Preview(showBackground = true)
@Composable
fun ShowsSearchBarWithTextPreview() {
    var query by remember { mutableStateOf("The Office") }
    ShowsSearchBar(query = query, onQueryChange = { query = it })
}

