package com.nramos.cabifymobilechallenge.core.data.datasource

import com.nramos.cabifymobilechallenge.core.data.di.IoDispatcher
import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartDatasource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    //Cache simulating backend
    private val cartOrder: Order = Order(mutableListOf())

    suspend fun getOrder() = withContext(ioDispatcher) {
        cartOrder
    }

    //Simulates adding one item to the cart based on quantity or unit
    suspend fun addToCart(cartItem: CartItem) =
        withContext(ioDispatcher) {
            cartOrder.items.find { it.product.code == cartItem.product.code }?.let {
                it.quantity = it.quantity?.plus(cartItem.quantity ?: 1)
            } ?: run {
                cartOrder.items.add(cartItem)
            }

            cartOrder
        }

    //Simulates removing one item to the cart based on quantity or unit
    suspend fun removeFromCart(cartItem: CartItem) =
        withContext(ioDispatcher) {
            if (cartItem in cartOrder.items) {
                cartOrder.items
                    .find { it == cartItem }
                    .also { it?.quantity?.minus(cartItem.quantity ?: 1) }
                    .also {
                        if (it?.quantity == 0) {
                            cartOrder.items.remove(cartItem)
                        }
                    }
            }

            cartOrder
        }

}