package com.nramos.core.domain.usecase

import com.nramos.core.domain.model.CartItem
import com.nramos.core.domain.model.Order
import com.nramos.core.domain.model.Product
import com.nramos.core.domain.repository.CartRepository
import javax.inject.Inject

class AddItemToCart @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(product: Product): Order {
        return cartRepository.addItemToCart(
            CartItem(
                product = product,
                quantity = 1
            )
        )
    }

}