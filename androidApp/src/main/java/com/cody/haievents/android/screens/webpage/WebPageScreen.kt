package com.cody.haievents.android.screens.webpage

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cody.haievents.android.common.components.CommonTopBar

@Composable
fun WebPageScreen(
    uiState: TermsConditionsUiState = TermsConditionsUiState(),
    clickBack: () -> Unit = {}
) {
    val title = uiState.homePageData?.type.orEmpty()
    val url = uiState.homePageData?.content

    Scaffold(
        topBar = { CommonTopBar(title, clickBack) }
    ) { paddingValues ->

        Box( // <- gives BoxScope so .align works
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage ?: "Something went wrong",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp)
                    )
                }

                else -> {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { ctx ->
                            WebView(ctx).apply {
                                layoutParams = ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                webViewClient = object : WebViewClient() {
                                    override fun shouldOverrideUrlLoading(
                                        view: WebView?,
                                        req: android.webkit.WebResourceRequest?
                                    ): Boolean = false
                                }
                                webChromeClient = WebChromeClient()
                            }
                        },
                        update = { webView ->
                            if (!url.isNullOrBlank() && webView.url != url) {
                                webView.loadUrl(url)
                            }
                        },
                        onRelease = { webView ->
                            webView.stopLoading()
                            webView.clearHistory()
                            webView.removeAllViews()
                            webView.destroy()
                        }
                    )
                }
            }
        }
    }
}
