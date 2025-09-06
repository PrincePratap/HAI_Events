package com.cody.haievents.android.screens.blogsDetail

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel


private const val TAG = "BlogsDetailRoute"

@Destination
@Composable
fun BlogsDetail( blogId: Int) {

    val viewModel: BlogsDetailViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Log.d(TAG, "Starting fetchBlogsDetail()")
        viewModel.fetchBlogDetail(blogId)
    }

    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            Log.e(TAG, "Side Effect: Error state observed. Showing Toast for message: '$error'")
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.onErrorMessageShown()
        }
    }
    BlogsDetailScreen (uiState = uiState)

}