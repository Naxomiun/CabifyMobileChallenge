package com.nramos.cabifymobilechallenge.core.domain.model

data class CartItem(
    val product: Product,
    var quantity: Int? = null
)