package com.nramos.core.data.datasource

import com.nramos.core.data.di.IoDispatcher
import com.nramos.core.data.mapper.ProductsMapper
import com.nramos.core.data.services.ProductService
import com.nramos.core.domain.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsDatasource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productService: ProductService
) {

    suspend fun getProducts(): List<Product> =
        withContext(ioDispatcher) {
            try {
                val response = productService.getProducts()
                ProductsMapper.fromProductsResponseToModel(response)
            } catch (e: Exception) {
                // Handle exception if needed
                emptyList()
            }
        }

}