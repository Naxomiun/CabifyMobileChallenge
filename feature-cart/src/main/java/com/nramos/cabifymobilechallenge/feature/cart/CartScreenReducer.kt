package com.nramos.cabifymobilechallenge.feature.cart

import com.nramos.cabifymobilechallenge.core.presentation.redux.Reducer

class CartScreenReducer : Reducer<CartScreenState, CartScreenAction> {

    override fun reduce(currentState: CartScreenState, action: CartScreenAction): CartScreenState {
        return when (action) {
            is CartScreenAction.FetchOrderStarted -> {
                currentState.copy (
                    orderLoading = true
                )
            }
            is CartScreenAction.FetchOrderSucceed -> {
                currentState.copy (
                    orderLoading = false,
                    order = action.order
                )
            }
            is CartScreenAction.ItemRemovingStarted -> {
                currentState.copy (
                    spinnerLoading = true
                )
            }
            is CartScreenAction.ItemRemovingSucceed -> {
                currentState.copy (
                    spinnerLoading = false,
                    order = action.order
                )
            }
            else -> currentState
        }
    }

}