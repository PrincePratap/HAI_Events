package com.cody.haievents.android.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.destinations.ShowDetailedDestination
import com.cody.haievents.android.screens.homePage.HomePageViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun  Search(navigator: DestinationsNavigator) {

    val viewModel: SearchViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    SearchScreen(
        uiState = uiState ,
        onQueryChange = {viewModel.updateQuery(it) },
        navigateToShowDetails = { navigator.navigate(ShowDetailedDestination(it))
        }
    )
}