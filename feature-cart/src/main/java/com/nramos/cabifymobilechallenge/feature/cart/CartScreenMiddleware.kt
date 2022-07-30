package com.nramos.cabifymobilechallenge.feature.cart

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.usecase.AddItemToCart
import com.nramos.cabifymobilechallenge.core.domain.usecase.GetCartOrder
import com.nramos.cabifymobilechallenge.core.domain.usecase.GetProducts
import com.nramos.cabifymobilechallenge.core.domain.usecase.RemoveItemFromCart
import com.nramos.cabifymobilechallenge.core.presentation.redux.Action
import com.nramos.cabifymobilechallenge.core.presentation.redux.Middleware
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.math.acosh

class CartScreenMiddleware @Inject constructor(
    private val getCartOrder: GetCartOrder,
    private val removeItemFromCart: RemoveItemFromCart
) : Middleware<CartScreenState, CartScreenAction> {

    override suspend fun process(
        action: CartScreenAction,
        currentState: CartScreenState,
        store: Store<CartScreenState, CartScreenAction>
    ) {
        when (action) {
            is CartScreenAction.FetchOrder -> processFetchOrderAction(store)
            is CartScreenAction.RemoveItemClicked -> processRemoveItemAction(store, action.item)
            else -> {}
        }
    }

    private suspend fun processFetchOrderAction(store: Store<CartScreenState, CartScreenAction>) {
        store.dispatch(CartScreenAction.FetchOrderStarted)
        val order = getCartOrder()
        store.dispatch(CartScreenAction.FetchOrderSucceed(order = order))
    }

    private suspend fun processRemoveItemAction(store: Store<CartScreenState, CartScreenAction>, cartItem: CartItem) {
        store.dispatch(CartScreenAction.ItemRemovingStarted)
        delay(1000) //Simulate network call
        val order = removeItemFromCart(cartItem)
        store.dispatch(CartScreenAction.ItemRemovingSucceed(order = order))
    }

}
