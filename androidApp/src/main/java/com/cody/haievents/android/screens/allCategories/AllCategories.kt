package com.cody.haievents.android.screens.allCategories

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.destinations.CategoriesShowsDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "AllCategoriesRoute"

@Destination
@Composable
fun AllCategories(
    navigator: DestinationsNavigator
) {
    Log.d(TAG, "Composable entered composition.")

    val viewModel: AllCategoriesViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Log.d(TAG, "Triggering fetchAllCategories() on first composition.")
        viewModel.fetchAllCategories()
    }

    AllCategoriesScreen(
        uiState = uiState,
        onCategoryClick = { navigator.navigate(CategoriesShowsDestination(categoryItems = it)) })

    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            Log.e(TAG, "Error observed in uiState. Showing Toast: '$error'")
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.onErrorMessageShown()
        }
    }
}
