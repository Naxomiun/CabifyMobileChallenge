package com.nramos.cabifymobilechallenge.core.domain.usecase

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.repository.CartRepository
import javax.inject.Inject

class RemoveItemFromCart @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(cartItem: CartItem) {
        cartRepository.removeItemFromCart(
            cartItem
        )
    }

}