package com.nramos.core.data.repository

import com.nramos.core.data.datasource.ProductsDatasource
import com.nramos.core.domain.model.Product
import com.nramos.core.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val datasource: ProductsDatasource
) : ProductsRepository {

    override suspend fun getProducts(): List<Product> {
        return datasource.getProducts()
    }

}