package com.nramos.cabifymobilechallenge.core.data.datasource

import com.nramos.cabifymobilechallenge.core.data.di.IoDispatcher
import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartDatasource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    //Cache simulating backend
    private val cartOrder: Order = Order( 0.00, 0.00, mutableListOf())

    suspend fun getOrder() = withContext(ioDispatcher) {
        cartOrder
    }

    //Simulates backend adding one item to the cart based on quantity or unit
    suspend fun addToCart(product: Product) =
        withContext(ioDispatcher) {
            val cartItem = CartItem(
                product = product,
                pricePerUnit = product.price,
                pricePerUnitWithDiscount = product.price,
                quantity = 1,
                totalPrice = product.price,
                totalPriceWithDiscounts = product.price
            )
            cartOrder.items.find { it.product.code == cartItem.product.code }?.let {
                it.quantity = it.quantity.plus(cartItem.quantity)
            } ?: run {
                cartOrder.items.add(cartItem)
            }
        }

    //Simulates backend removing one item to the cart based on quantity or unit
    suspend fun removeFromCart(cartItem: CartItem) =
        withContext(ioDispatcher) {
            cartOrder.items.find { it.product.code == cartItem.product.code }?.let {
                cartOrder.items.remove(it)
            }
        }

}