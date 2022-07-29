package com.nramos.cabifymobilechallenge.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nramos.cabifymobilechallenge.core.navigation.Destination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState = remember(navController, coroutineScope) {
    AppState(navController, coroutineScope)
}

class AppState(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    fun navigate(destination: Destination) {
        navController.navigate(destination.route)
    }

    fun onBackClick() {
        navController.popBackStack()
    }

}