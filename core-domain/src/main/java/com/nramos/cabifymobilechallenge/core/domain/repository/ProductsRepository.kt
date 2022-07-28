package com.nramos.cabifymobilechallenge.core.domain.repository

import com.nramos.cabifymobilechallenge.core.domain.model.Product

interface ProductsRepository {

    suspend fun getProducts(): List<Product>

}