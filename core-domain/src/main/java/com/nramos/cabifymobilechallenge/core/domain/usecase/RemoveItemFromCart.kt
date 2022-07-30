package com.nramos.cabifymobilechallenge.core.domain.usecase

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.repository.CartRepository
import javax.inject.Inject

class RemoveItemFromCart @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(cartItem: CartItem): Order {
        cartRepository.removeItemFromCart(cartItem)
        return cartRepository.getCartOrder()
    }

}