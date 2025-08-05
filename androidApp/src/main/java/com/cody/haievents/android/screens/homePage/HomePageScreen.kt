package com.cody.haievents.android.screens.homePage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cody.haievents.android.common.components.HomeScreenHeader
import com.cody.haievents.android.common.componets.EventCategoriesItems
import com.cody.haievents.android.common.componets.TheatreShowsCard

@Composable
fun  HomePageScreen(
    uiState: HomepageUiState,
    onRetry: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()){
        HomeScreenHeader()
        EventCategoriesItems()
        TheatreShowsCard(uiState.homePageData!!.featured[0])
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
//    HomePageScreen()
}