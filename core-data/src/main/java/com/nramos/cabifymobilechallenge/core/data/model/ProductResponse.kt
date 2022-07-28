package com.nramos.cabifymobilechallenge.core.data.model

data class ProductsResponse(
    val products: List<ProductResponse>
)

data class ProductResponse(
    val code: String,
    val name: String,
    val price: Double
)
