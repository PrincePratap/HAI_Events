package com.cody.haievents.android.screens.GaneshTheater

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.SeatKey
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockFirst
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockSecond
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockThird
import androidx.compose.material3.*
import com.cody.haievents.android.common.componets.ZoomableCanvas
import java.text.NumberFormat
import java.util.Locale

@Composable
fun GaneshTheaterScreen(
    uiState: GaneshTheaterUiState,
    onBackClick: () -> Unit = {},
    clickOnProceed : (String) -> Unit = {},


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

    // row -> meta (color / price) for quick lookups (case-insensitive)
    val rowMeta = remember(uiState.seatConfig) {
        uiState.seatConfig?.seatConfig?.associateBy { it.row.uppercase() } ?: emptyMap()
    }

    // total price of selected seats
    val totalPrice = remember(selected, rowMeta) {
        selected.sumOf { seat -> rowMeta[seat.row.uppercase()]?.price ?: 0 }
    }
    val totalPriceText = remember(totalPrice) {
        "₹" + NumberFormat.getNumberInstance(Locale("en", "IN")).format(totalPrice)
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Ganesh Theater",
                onBackClick =   {
                    onBackClick()
                })

        },
        bottomBar = {
            Surface(tonalElevation = 3.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Selected: ${selected.size}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Total: $totalPriceText",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            enabled = selected.isNotEmpty(),
                            onClick = { selected = emptySet() }
                        ) { Text("Clear") }

                        Button(
                            enabled = selected.isNotEmpty(),
                            onClick = {
                                clickOnProceed(totalPriceText)
                            }
                        ) { Text("Proceed") }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding)) {
            when {
                uiState.isLoading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }

                uiState.errorMessage != null -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text(uiState.errorMessage ?: "Something went wrong")
                }

                uiState.seatConfig != null -> {
                    ZoomableCanvas(
                        modifier = Modifier,
                    ) { scale ->
                        val scrollEnabled = scale <= 1f + 1e-3f

                        Column(
                            modifier = Modifier
                                .verticalScroll(vScroll, enabled = scrollEnabled)
                                .horizontalScroll(hScroll, enabled = scrollEnabled)
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Top → Bottom: Third, Second, First (as in your current code)
                            GaneshTheaterBlockThird(
                                response = uiState.seatConfig!!,
                                selectedSeats = selected,
                                bookedSeats = booked
                            ) { key -> selected = if (key in selected) selected - key else selected + key }

                            GaneshTheaterBlockSecond(
                                response = uiState.seatConfig!!,
                                selectedSeats = selected,
                                bookedSeats = booked
                            ) { key -> selected = if (key in selected) selected - key else selected + key }

                            GaneshTheaterBlockFirst(
                                response = uiState.seatConfig!!,
                                selectedSeats = selected,
                                bookedSeats = booked
                            ) { key -> selected = if (key in selected) selected - key else selected + key }
                        }
                    }
                }

                else -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text("No seat configuration available.")
                }
            }
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
