package com.nramos.cabifymobilechallenge.core.navigation

sealed class Destination {

    abstract val route: String

    object Products: Destination() {
        override val route: String
            get() = "products"
    }

    object Cart: Destination() {
        override val route: String
            get() = "cart"
    }

}

