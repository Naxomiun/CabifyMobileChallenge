package com.nramos.cabifymobilechallenge.core.domain.usecase

import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.repository.CartRepository
import javax.inject.Inject

class GetCartOrderUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(): Order {
        return cartRepository.getCartOrder()
    }

}