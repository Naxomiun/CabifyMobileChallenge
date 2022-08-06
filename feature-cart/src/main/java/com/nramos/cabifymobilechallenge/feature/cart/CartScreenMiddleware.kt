package com.nramos.cabifymobilechallenge.feature.cart

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.usecase.GetCartOrderUseCase
import com.nramos.cabifymobilechallenge.core.domain.usecase.RemoveItemFromCartUseCase
import com.nramos.cabifymobilechallenge.core.presentation.redux.Middleware
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store
import kotlinx.coroutines.delay
import javax.inject.Inject

class CartScreenMiddleware @Inject constructor(
    private val getCartOrderUseCase: GetCartOrderUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase
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
        val order = getCartOrderUseCase()
        store.dispatch(CartScreenAction.FetchOrderSucceed(order = order))
    }

    private suspend fun processRemoveItemAction(store: Store<CartScreenState, CartScreenAction>, cartItem: CartItem) {
        store.dispatch(CartScreenAction.ItemRemovingStarted)
        delay(1000) //Simulate network call
        val order = removeItemFromCartUseCase(cartItem)
        store.dispatch(CartScreenAction.ItemRemovingSucceed(order = order))
    }

}
