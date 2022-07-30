package com.nramos.cabifymobilechallenge.core.domain.repository

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.model.Product

interface CartRepository {

    suspend fun getCartOrder(): Order

    suspend fun addItemToCart(product: Product)

    suspend fun removeItemFromCart(cartItem: CartItem)

}