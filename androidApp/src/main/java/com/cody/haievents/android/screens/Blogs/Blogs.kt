package com.cody.haievents.android.screens.Blogs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cody.haievents.android.screens.destinations.BlogsDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "BlogsComposable"

@Destination
@Composable
fun Blogs(
    navigator: DestinationsNavigator
) {
    Log.d(TAG, "Entered Blogs screen composition")

    val viewModel: BlogsViewModel = koinViewModel()
    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {
        Log.d(TAG, "LaunchedEffect → Triggering fetchBlogs()")
        viewModel.fetchBlogs()
    }

    Log.d(
        TAG,
        "Rendering BlogsScreen → isLoading=${uiState.isLoading}, " +
                "succeed=${uiState.succeed}, " +
                "error=${uiState.errorMessage}, " +
                "blogs=${uiState.blogsPageData?.blogs?.size ?: 0}"
    )

    BlogsScreen(
        uiState = uiState,
        onBackPressed = { navigator.popBackStack() },
        onBlogClick = { blogId -> navigator.navigate(BlogsDetailDestination(blogId = blogId)) })
}
