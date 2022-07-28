package com.nramos.core.domain.usecase

import com.nramos.core.domain.repository.CartRepository
import javax.inject.Inject

class GetCartOrder @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke() = cartRepository.getCartOrder()

}