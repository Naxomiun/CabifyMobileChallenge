package com.nramos.cabifymobilechallenge.ui

import androidx.compose.runtime.Composable
import com.nramos.cabifymobilechallenge.navigation.CabifyNavHost
import com.nramos.cabifymobilechallenge.ui.theme.CabifyChallengeTheme

@Composable
fun CabifyApp(
    appState: AppState = rememberAppState()
) {
    MainContent {
        CabifyNavHost(
            navController = appState.navController,
            onNavigation = appState::navigate,
            onBackClick = appState::onBackClick
        )
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