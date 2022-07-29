package com.nramos.cabifymobilechallenge.feature.cart

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.presentation.redux.Action

sealed interface CartScreenAction : Action {
    object FetchOrder: CartScreenAction
    object FetchOrderStarted : CartScreenAction
    data class FetchOrderSucceed(val order: Order) : CartScreenAction

    data class RemoveItemClicked(val item: CartItem) : CartScreenAction
    object ItemRemovingStarted : CartScreenAction
    data class ItemRemovingSucceed(val order: Order) : CartScreenAction
    //Implement error handling if needed
}