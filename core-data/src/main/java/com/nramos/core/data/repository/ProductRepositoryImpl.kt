package com.nramos.core.data.repository

import com.nramos.core.data.datasource.DiscountsDatasource
import com.nramos.core.data.datasource.ProductsDatasource
import com.nramos.core.data.mapper.DiscountsMapper
import com.nramos.core.domain.model.Product
import com.nramos.core.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productsDatasource: ProductsDatasource,
    private val discountsDatasource: DiscountsDatasource
) : ProductsRepository {

    override suspend fun getProducts(): List<Product> {

        val discounts = DiscountsMapper.fromDiscountsResponseToModel()

        return productsDatasource.getProducts()
    }

}