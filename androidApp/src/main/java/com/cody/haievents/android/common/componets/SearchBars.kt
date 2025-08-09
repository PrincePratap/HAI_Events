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
fun ShowsSearchBar() {
    val goldColor = Color(0xFFDDB95E)
    val cornerRadius = 16.dp

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                width = 1.5.dp,
                color = goldColor,
                shape = RoundedCornerShape(cornerRadius)
            ),
        shape = RoundedCornerShape(cornerRadius),
        color = MaterialTheme.colorScheme.surface // Typically white in a light theme
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurface // Typically black in a light theme
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Shows",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ShowsSearchBarPreview() {
    ShowsSearchBar()

}