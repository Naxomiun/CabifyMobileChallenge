package com.nramos.cabifymobilechallenge.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    cartScreenMiddleware: CartScreenMiddleware,
) : ViewModel() {

    private val store = Store(
        initialState = CartScreenState(),
        reducer = CartScreenReducer(),
        middlewares = listOf(
            cartScreenMiddleware
        )
    )

    val viewState: StateFlow<CartScreenState> = store.state

    init {
        viewModelScope.launch {
            store.dispatch(CartScreenAction.FetchOrder)
        }
    }

    fun removeItemFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            store.dispatch(CartScreenAction.RemoveItemClicked(cartItem))
        }
    }

}