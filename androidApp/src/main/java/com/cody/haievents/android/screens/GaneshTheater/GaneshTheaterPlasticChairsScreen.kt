package com.cody.haievents.android.screens.GaneshTheater

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.SeatKey
import com.cody.haievents.android.common.componets.ZoomableCanvas
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockFirst
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockSecond
import com.cody.haievents.android.common.componets.theater.GaneshTheaterBlockThird
import java.text.NumberFormat
import java.util.Locale



@Composable
fun GaneshTheaterPlasticChairsScreen(
    uiState: GaneshTheaterUiState,
    onBackClick: () -> Unit = {},
    clickOnProceed: (String) -> Unit = {},
    // a small safeguard to guarantee horizontal overflow when rows aren’t wide enough
    minSeatMapWidth: Dp = 1000.dp
) {
    val hScroll = rememberScrollState()

    // Persist selections across config changes
    var selected by rememberSaveable { mutableStateOf(setOf<SeatKey>()) }

    // reserved → SeatKey
    val booked: Set<SeatKey> = remember(uiState.seatConfig) {
        uiState.seatConfig
            ?.seatConfig
            ?.flatMap { row -> row.reservedSeats.map { SeatKey(row.row, it) } }
            ?.toSet() ?: emptySet()
    }

    // row -> meta (color/price) for quick lookups (case-insensitive)
    val rowMeta = remember(uiState.seatConfig) {
        uiState.seatConfig?.seatConfig?.associateBy { it.row.uppercase() } ?: emptyMap()
    }

    // Memoize formatter to avoid recreating
    val moneyFmt = remember { NumberFormat.getNumberInstance(Locale("en", "IN")) }

    // total price of selected seats
    val totalPrice = remember(selected, rowMeta) {
        selected.sumOf { seat -> rowMeta[seat.row.uppercase()]?.price ?: 0 }
    }
    val totalPriceText = remember(totalPrice) { "₹" + moneyFmt.format(totalPrice) }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Ganesh Theater",
                onBackClick = onBackClick
            )
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
                            onClick = { clickOnProceed(totalPriceText) }
                        ) { Text("Proceed") }
                    }
                }
            }
        },
        // Only apply system bar insets to the TOP; we’ll handle bottom ourselves.
        contentWindowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Top)
    ) { innerPadding ->

        // keep top/sides from Scaffold, drop bottom so there’s no big white band
        val contentPad = PaddingValues(
            top = innerPadding.calculateTopPadding(),
            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
            // bottom intentionally omitted
        )

        Box(Modifier.fillMaxSize().padding(contentPad)) {
            when {
                uiState.isLoading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }

                uiState.errorMessage != null -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text(uiState.errorMessage ?: "Something went wrong")
                }

                uiState.seatConfig != null -> {
                    ZoomableCanvas(
                        modifier = Modifier.fillMaxSize(),
                        minScale = 1f
                    ) { _ /* scale */ ->
                        // Horizontal scroll parent with bounded height (fillMaxSize)
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .horizontalScroll(hScroll)
                        ) {
                            // Vertical scroll via LazyColumn (bounded by fillMaxHeight)
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxHeight()           // bounded height → avoids infinite constraints
                                    .requiredWidthIn(min = minSeatMapWidth) // ensure horizontal overflow exists
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                contentPadding = PaddingValues(bottom = 8.dp)
                            ) {
                                item {
                                    GaneshTheaterBlockThird(
                                        response = uiState.seatConfig,
                                        selectedSeats = selected,
                                        bookedSeats = booked
                                    ) { key ->
                                        selected = if (key in selected) selected - key else selected + key
                                    }
                                }
                                item {
                                    GaneshTheaterBlockSecond(
                                        response = uiState.seatConfig,
                                        selectedSeats = selected,
                                        bookedSeats = booked
                                    ) { key ->
                                        selected = if (key in selected) selected - key else selected + key
                                    }
                                }
                                item {
                                    GaneshTheaterBlockFirst(
                                        response = uiState.seatConfig,
                                        selectedSeats = selected,
                                        bookedSeats = booked
                                    ) { key ->
                                        selected = if (key in selected) selected - key else selected + key
                                    }
                                }
                            }
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
private fun GaneshTheaterPlasticChairsScreenPreview() {
    GaneshTheaterPlasticChairsScreen(
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