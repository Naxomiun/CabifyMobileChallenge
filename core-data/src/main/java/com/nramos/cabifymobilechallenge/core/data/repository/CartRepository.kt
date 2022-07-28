package com.nramos.cabifymobilechallenge.core.data.repository

import com.nramos.cabifymobilechallenge.core.data.datasource.CartDatasource
import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.repository.CartRepository
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