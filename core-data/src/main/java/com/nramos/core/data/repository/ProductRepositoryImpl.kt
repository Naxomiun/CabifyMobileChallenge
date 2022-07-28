package com.nramos.core.data.repository

import com.nramos.core.data.datasource.DiscountsDatasource
import com.nramos.core.data.datasource.ProductsDatasource
import com.nramos.core.data.mapper.DiscountsMapper
import com.nramos.core.domain.model.Discount
import com.nramos.core.domain.model.Product
import com.nramos.core.domain.repository.ProductsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productsDatasource: ProductsDatasource,
    private val discountsDatasource: DiscountsDatasource
) : ProductsRepository {

    override suspend fun getProducts(): List<Product> {
        return coroutineScope {
            val products = async { productsDatasource.getProducts() }
            val discounts = async { discountsDatasource.getDiscounts() }
            setDiscountsToProducts(products.await(), discounts.await())
        }
    }

    private fun setDiscountsToProducts(
        products: List<Product>,
        discounts: List<Discount>
    ): List<Product> {
        return products.map { product ->
            product.copy(discount = discounts.find { it.appliesTo == product.type })
        }
    }

}