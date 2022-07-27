package com.nramos.core.domain.usecase

import com.nramos.core.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke() = productsRepository.getProducts()

}