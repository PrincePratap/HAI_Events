package com.cody.haievents.android.screens.homePage

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.main.MainActivity
import com.cody.haievents.android.screens.destinations.AllCategoriesDestination
import com.cody.haievents.android.screens.destinations.SearchDestination
import com.cody.haievents.android.screens.destinations.ShowDetailedDestination

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "HomePageRoute"

@Destination()
@Composable
fun HomePage(
    navigator: DestinationsNavigator,
) {
    Log.d(TAG, "HomePage Composable entered composition.")

    val viewModel: HomePageViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current




    LaunchedEffect(Unit) {
        Log.d(TAG, "Starting fetchHomePage()")
        viewModel.fetchHomePageData()
    }

    HomePageScreen(
        uiState = uiState,
        onRetry = {
            Log.d(TAG, "UI Event: onRetry clicked.")
//            viewModel.fetchHomePageData()
        },
        navigateToShowDetails = {
            navigator.navigate(ShowDetailedDestination(it))
        },
        navigateToSearchScreen = {
            navigator.navigate(SearchDestination.route)
        },
        navigateToCategoryScreen = {
            navigator.navigate(AllCategoriesDestination.route)
        },
        clickOnCategoryItem = {
            // Implement click on category item
        }

    )

        LaunchedEffect(key1 = uiState.errorMessage) {
            uiState.errorMessage?.let { error ->
                Log.e(TAG, "Side Effect: Error state observed. Showing Toast for message: '$error'")
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                viewModel.onErrorMessageShown()
            }
        }
}

//@Composable
//fun PayWithPhonePeButton(orderId: String, token: String) {
//    Button(onClick = {  }) {
//        Text("Pay with PhonePe")
//    }
//}