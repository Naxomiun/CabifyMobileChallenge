package com.nramos.cabifymobilechallenge.navigation

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nramos.cabifymobilechallenge.core.navigation.Destination
import com.nramos.cabifymobilechallenge.core.navigation.NavCommand
import com.nramos.cabifymobilechallenge.feature.cart.CartScreen
import com.nramos.cabifymobilechallenge.feature.products.ProductsScreen

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
        modifier = modifier.background(MaterialTheme.colors.background)
    ) {
        composable(NavCommand.ContentType(Destination.Products)) {
            ProductsScreen(
                navigateToCart = onNavigation
            )
        }

        composable(NavCommand.ContentType(Destination.Cart)) {
            CartScreen()
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