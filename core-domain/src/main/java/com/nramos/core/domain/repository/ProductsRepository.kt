package com.nramos.core.domain.repository

import com.nramos.core.domain.model.Product

interface ProductsRepository {

    suspend fun getProducts(): List<Product>

}