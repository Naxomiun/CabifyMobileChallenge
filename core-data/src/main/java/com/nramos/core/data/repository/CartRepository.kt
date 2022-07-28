package com.nramos.core.data.repository

import com.nramos.core.data.datasource.CartDatasource
import com.nramos.core.domain.model.CartItem
import com.nramos.core.domain.model.Order
import com.nramos.core.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDatasource: CartDatasource
) : CartRepository {

    override suspend fun getCartOrder(): Order = cartDatasource.getOrder()

    override suspend fun addItemToCart(cartItem: CartItem): Order {
        return cartDatasource.addToCart(cartItem)
    }

    override suspend fun removeItemFromCart(cartItem: CartItem): Order {
        return cartDatasource.removeFromCart(cartItem)
    }


}