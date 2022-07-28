package com.nramos.cabifymobilechallenge.core.data.mapper

import com.nramos.cabifymobilechallenge.core.data.model.ProductsResponse
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.domain.model.ProductType

object ProductsMapper {

    fun fromProductsResponseToModel(productsResponse: ProductsResponse): List<Product> {
        return productsResponse.products.map {
            val productType = ProductType.fromCode(it.code)
            Product(
                name = it.name,
                type = productType,
                code = it.code,
                price = it.price,
                currencyCode = getCurrencyCode(),
                mediaUrl = getMediaUrlForProducts(productType)
            )
        }
    }

    private fun getMediaUrlForProducts(productType: ProductType): String {
        return when(productType) {
            ProductType.UNKNOWN -> ""
            ProductType.VOUCHER -> "https://theemperorsoldclothes.co.uk/wp-content/uploads/2019/11/Group-324.jpg"
            ProductType.TSHIRT -> "https://sportofino.com/media/catalog/product/cache/d0e194cae1992f3f4431fdfab9f8e16a/0/8/080_17100495_white_02.jpg"
            ProductType.MUG -> "https://www.reusablecoffeecupexperts.com.au/35536-large_default/300ml-purple-sorrento-coffee-mug.jpg"
        }
    }

    private fun getCurrencyCode(): String {
        return "€"
    }

}