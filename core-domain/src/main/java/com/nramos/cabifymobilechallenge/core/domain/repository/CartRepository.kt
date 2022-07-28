package com.nramos.cabifymobilechallenge.core.domain.repository

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order

interface CartRepository {

    suspend fun getCartOrder(): Order

    suspend fun addItemToCart(cartItem: CartItem): Order

    suspend fun removeItemFromCart(cartItem: CartItem): Order

}