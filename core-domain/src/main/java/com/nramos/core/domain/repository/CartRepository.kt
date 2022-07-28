package com.nramos.core.domain.repository

import com.nramos.core.domain.model.CartItem
import com.nramos.core.domain.model.Order

interface CartRepository {

    suspend fun getCartOrder(): Order

    suspend fun addItemToCart(cartItem: CartItem): Order

    suspend fun removeItemFromCart(cartItem: CartItem): Order

}