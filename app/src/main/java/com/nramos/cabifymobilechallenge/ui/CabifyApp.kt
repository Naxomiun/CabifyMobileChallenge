package com.nramos.cabifymobilechallenge.ui

import androidx.compose.runtime.Composable
import com.nramos.cabifymobilechallenge.ui.theme.CabifyChallengeTheme
import com.nramos.feature.products.ProductsScreen

@Composable
fun CabifyApp(
    appState: AppState = rememberAppState()
) {
    MainContent {
        ProductsScreen()
    }
}

@Composable
fun MainContent(
    content: @Composable () -> Unit
) {
    CabifyChallengeTheme {
        content()
    }
}