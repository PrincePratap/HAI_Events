package com.cody.haievents.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

import com.cody.haievents.android.common.componets.BestEventsSection
import com.cody.haievents.android.common.componets.CategoriesComponent
import com.cody.haievents.android.common.componets.RecommendedMovies
import com.cody.haievents.android.common.theming.MyBackgroundColor


@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyBackgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        CategoriesComponent()
        RecommendedMovies()
        BestEventsSection()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MyBackgroundColor)
        ) {
            HomeScreen()
        }
    }
}