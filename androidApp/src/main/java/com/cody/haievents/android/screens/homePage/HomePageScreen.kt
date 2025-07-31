package com.cody.haievents.android.screens.homePage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cody.haievents.android.common.components.HomeScreenHeader
import com.cody.haievents.android.common.componets.EventCategoriesItems

@Composable
fun  HomePageScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeScreenHeader()
        EventCategoriesItems()
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePageScreen()
}