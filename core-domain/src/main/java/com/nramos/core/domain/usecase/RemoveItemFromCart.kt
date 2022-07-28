package com.nramos.core.domain.usecase

import com.nramos.core.domain.model.CartItem
import com.nramos.core.domain.model.Product
import com.nramos.core.domain.repository.CartRepository
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