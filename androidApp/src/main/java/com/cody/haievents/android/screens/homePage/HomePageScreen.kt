package com.cody.haievents.android.screens.homePage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.components.HomeScreenHeader
import com.cody.haievents.android.common.componets.EventCategoriesItems
import com.cody.haievents.android.common.componets.TheatreShowsCard
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun HomePageScreen(
    uiState: HomepageUiState,
    onRetry: () -> Unit,
    navigateToShowDetails: (Int) -> Unit,
    paymentWithRazorPay : () -> Unit
) {
    // LazyColumn is used for displaying scrollable lists of items efficiently.
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        item { HomeScreenHeader() }
        item { EventCategoriesItems() }
        uiState.homePageData?.featured?.firstOrNull()?.let { featuredShow ->
            item { TheatreShowsCard(item = featuredShow ,
                onItemClick = {
//                    paymentWithRazorPay()
                    navigateToShowDetails(it.id)
            }) }
        }


    }
}

