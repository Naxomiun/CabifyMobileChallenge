package com.nramos.cabifymobilechallenge.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nramos.cabifymobilechallenge.core.navigation.Destination
import com.nramos.cabifymobilechallenge.core.navigation.NavCommand
import com.nramos.feature.products.ProductsScreen

@Composable
fun CabifyNavHost(
    navController: NavHostController,
    onNavigation: (Destination) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Products.route,
        modifier = modifier,
    ) {
        composable(NavCommand.ContentType(Destination.Products)) {
            ProductsScreen(
                navigateToCart = onNavigation
            )
        }
    }
}


private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}