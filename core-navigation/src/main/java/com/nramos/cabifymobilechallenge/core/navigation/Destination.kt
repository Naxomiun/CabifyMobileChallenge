package com.nramos.cabifymobilechallenge.core.navigation

sealed class Destination(val route: String) {
    object Products: Destination(route = "products")
    object Cart: Destination(route = "cart")
}

