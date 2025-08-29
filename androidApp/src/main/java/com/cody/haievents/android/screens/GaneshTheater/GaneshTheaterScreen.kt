package com.cody.haievents.android.screens.GaneshTheater

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.android.common.componets.SeatKey
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockFirst
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockSecond
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockThird
import kotlin.math.max
import kotlin.math.min

/* ---------------- Zoom wrapper ---------------- */

@Composable
private fun ZoomableCanvas(
    modifier: Modifier = Modifier,
    minScale: Float = 0.5f,
    maxScale: Float = 3f,
    // expose scale if you want to show % somewhere
    content: @Composable (scale: Float) -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var translation by remember { mutableStateOf(Offset.Zero) }

    val transformState = rememberTransformableState { zoomChange, panChange, _ ->
        // pinch-to-zoom
        val newScale = (scale * zoomChange).coerceIn(minScale, maxScale)
        // adjust pan responsiveness as scale changes
        val pan = panChange * (if (newScale > 1f) 1f else 0f)
        scale = newScale
        translation += pan
    }

    // double-tap to toggle zoom (center-ish)
    val doubleTapModifier = Modifier.pointerInput(Unit) {
        detectTapGestures(
            onDoubleTap = {
                if (scale <= 1f + 1e-3f) {
                    scale = 2f.coerceIn(minScale, maxScale)
                } else {
                    scale = 1f
                    translation = Offset.Zero
                }
            }
        )
    }

    Box(
        modifier = modifier
            .then(doubleTapModifier)
            .transformable(transformState) // pinch + pan gestures
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = translation.x,
                    translationY = translation.y
                )
        ) {
            content(scale)
        }

        // Optional: simple zoom controls (overlay)
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(tonalElevation = 2.dp, shape = MaterialTheme.shapes.small) {
                Row {
                    IconButton(onClick = {
                        scale = max(1f, (scale - 0.25f)).coerceAtLeast(minScale)
                        if (scale == 1f) translation = Offset.Zero
                    }) { Icon(androidx.compose.material.icons.Icons.Default.Remove, contentDescription = "Zoom out") }

                    IconButton(onClick = {
                        scale = min(maxScale, scale + 0.25f)
                    }) { Icon(androidx.compose.material.icons.Icons.Default.Add, contentDescription = "Zoom in") }
                }
            }
        }
    }
}

/* --------------- Your screen with zoom + scroll --------------- */

@Composable
fun GaneshTheaterScreen(
    uiState: GaneshTheaterUiState
) {
    val vScroll = rememberScrollState()
    val hScroll = rememberScrollState()

    // local selection
    var selected by remember { mutableStateOf(setOf<SeatKey>()) }

    // reserved → SeatKey
    val booked: Set<SeatKey> = remember(uiState.seatConfig) {
        uiState.seatConfig
            ?.seatConfig
            ?.flatMap { row -> row.reservedSeats.map { SeatKey(row.row, it) } }
            ?.toSet() ?: emptySet()
    }

    // We’ll control scroll enabling depending on zoom level (inside ZoomableCanvas)
    Box(Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> Box(Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }

            uiState.errorMessage != null -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text(uiState.errorMessage ?: "Something went wrong")
            }

            uiState.seatConfig != null -> {
                ZoomableCanvas(
                    modifier = Modifier
                        // Scroll only when not zoomed in (scale==1); ZoomableCanvas tells us scale
                        .let { base ->
                            // The inner lambda gets the scale; build modifiers at runtime
                            // We’ll attach scroll inside content below to know the scale
                            base
                        }
                ) { scale ->
                    // Enable scroll only at 1x to avoid gesture conflict with pan
                    val scrollEnabled = scale <= 1f + 1e-3f

                    Column(
                        modifier = Modifier
                            .then(
                                Modifier
                                    .verticalScroll(vScroll, enabled = scrollEnabled)
                                    .horizontalScroll(hScroll, enabled = scrollEnabled)
                            )
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Third (top), Second, First — your current order
                        GaneshTheaterBlockThird(
                            selectedSeats = selected,
                            bookedSeats = booked
                        ) { key -> selected = if (key in selected) selected - key else selected + key }

                        GaneshTheaterBlockSecond(
                            selectedSeats = selected,
                            bookedSeats = booked
                        ) { key -> selected = if (key in selected) selected - key else selected + key }

                        GaneshTheaterBlockFirst(
                            selectedSeats = selected,
                            bookedSeats = booked
                        ) { key -> selected = if (key in selected) selected - key else selected + key }
                    }
                }
            }

            else -> Box(Modifier.fillMaxSize(), Alignment.Center) { Text("No seat configuration available.") }
        }
    }
}

@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
private fun SeatBookingScreenPreview() {
    GaneshTheaterScreen(
        uiState = GaneshTheaterUiState(
            isLoading = false,
            succeed = true,
            seatConfig = GaneshTheaterGetSeatResponse(
                status = true,
                seatConfig = emptyList()
            )
        )
    )
}
