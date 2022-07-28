package com.nramos.feature.products

import com.nramos.core.presentation.redux.Reducer

class ProductsScreenReducer : Reducer<ProductsScreenState, ProductsScreenAction> {

    override fun reduce(currentState: ProductsScreenState, action: ProductsScreenAction): ProductsScreenState {
        return when (action) {
            is ProductsScreenAction.ProductAddedToCartStarted -> {
                currentState.copy(
                    spinnerLoading = true
                )
            }
            is ProductsScreenAction.ProductAddedToCartSucceed -> {
                currentState.copy(
                    order = action.order,
                    spinnerLoading = false
                )
            }
            is ProductsScreenAction.FetchProductsStarted -> {
                currentState.copy(
                    productsLoading = true
                )
            }
            is ProductsScreenAction.FetchProductsSucceed -> {
                currentState.copy(
                    products = action.products,
                    productsLoading = false
                )
            }
            else -> currentState
        }
    }

}