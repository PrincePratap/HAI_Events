package com.cody.haievents.android.common.componets

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.theming.GoldenYellow
import io.ktor.websocket.Frame


@Composable
fun CommonButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text : String = "Login",
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = GoldenYellow,
            contentColor = Color.White
        )
    ){
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

    }
}

@Preview(showBackground = true, name = "AppButton States")
@Composable
private fun AppButtonPreview() {
    // Using a Column to display multiple states of the button vertically.
//    CommonButton(
//        onClick = { /* Clicks do nothing in preview */ }
//    ) {
//        "Login"
//    }

    // Preview for the disabled state

}