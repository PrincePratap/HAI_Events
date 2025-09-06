package com.cody.haievents.android.screens.categorieShows

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.allCategories.AllCategoriesScreen
import com.cody.haievents.android.screens.allCategories.AllCategoriesViewModel
import com.cody.haievents.android.screens.destinations.ShowDetailedDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


private const val TAG = "CategoriesRoute"


@Destination
@Composable
fun  CategoriesShows(
    navigator: DestinationsNavigator,
    categoryItems :Int) {


    Log.d(TAG, "Composable entered composition.")

    val viewModel: CategoriesShowsViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Log.d(TAG, "Triggering fetchAllCategories() on first composition.")
        viewModel.fetchCategories(categoryItems)
    }

    CategoriesShowsScreen(
        uiState = uiState,
        onBackClick = { navigator.navigateUp() },
        onItemClick = {navigator.navigate(ShowDetailedDestination(showId = it))})

    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            Log.e(TAG, "Error observed in uiState. Showing Toast: '$error'")
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.onErrorMessageShown()
        }
    }
}