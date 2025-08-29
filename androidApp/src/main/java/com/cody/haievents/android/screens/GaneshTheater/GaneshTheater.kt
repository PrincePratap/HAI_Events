package com.cody.haievents.android.screens.GaneshTheater

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun GaneshTheater() {
    val viewModel: GaneshTheaterViewModel = koinViewModel()
    Log.d(TAG, "GaneshTheater Composable Loaded")

    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Log.d(TAG, "Starting getGaneshTheater()")
        viewModel.getGaneshTheater()
    }

    GaneshTheaterScreen(uiState = uiState)

    uiState.errorMessage?.let { error ->
        Log.e(TAG, "Error: $error")
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    if (uiState.succeed) {
        Log.d(TAG, "GaneshTheater success — data loaded")
    }
}

private const val TAG = "GaneshTheaterComposable"
