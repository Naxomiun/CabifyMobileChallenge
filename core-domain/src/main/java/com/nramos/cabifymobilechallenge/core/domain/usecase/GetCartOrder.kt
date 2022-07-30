package com.nramos.cabifymobilechallenge.core.domain.usecase

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Discount
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.repository.CartRepository
import javax.inject.Inject

class GetCartOrder @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(): Order {
        val cartOrder = cartRepository.getCartOrder()
        return cartOrder
    }

}