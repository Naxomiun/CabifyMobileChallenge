package com.nramos.feature.products

import com.nramos.core.domain.model.Order
import com.nramos.core.domain.model.Product
import com.nramos.core.presentation.redux.Action

sealed interface ProductsScreenAction : Action {
    data class AddToCartButtonClicked(val product: Product) : ProductsScreenAction
    object FetchProducts : ProductsScreenAction

    object FetchProductsStarted : ProductsScreenAction
    data class FetchProductsSucceed(val products: List<Product>) : ProductsScreenAction
    object ProductAddedToCartStarted : ProductsScreenAction
    data class ProductAddedToCartSucceed(val order: Order) : ProductsScreenAction
    //Implement error handling if needed
}