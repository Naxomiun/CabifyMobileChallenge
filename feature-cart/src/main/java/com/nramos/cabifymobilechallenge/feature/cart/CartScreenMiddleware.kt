package com.nramos.cabifymobilechallenge.feature.cart

import com.nramos.cabifymobilechallenge.core.domain.usecase.AddItemToCart
import com.nramos.cabifymobilechallenge.core.domain.usecase.GetCartOrder
import com.nramos.cabifymobilechallenge.core.domain.usecase.GetProducts
import com.nramos.cabifymobilechallenge.core.presentation.redux.Middleware
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store
import javax.inject.Inject

class CartScreenMiddleware @Inject constructor(
    private val getCartOrder: GetCartOrder
) : Middleware<CartScreenState, CartScreenAction> {

    override suspend fun process(
        action: CartScreenAction,
        currentState: CartScreenState,
        store: Store<CartScreenState, CartScreenAction>
    ) {
        when (action) {
            is CartScreenAction.FetchOrder -> processFetchOrderAction(store)
            is CartScreenAction.RemoveItemClicked -> TODO()
            else -> {}
        }
    }

    private suspend fun processFetchOrderAction(store: Store<CartScreenState, CartScreenAction>) {
        store.dispatch(CartScreenAction.FetchOrderStarted)
        val order = getCartOrder()
        store.dispatch(CartScreenAction.FetchOrderSucceed(order = order))
    }

}
