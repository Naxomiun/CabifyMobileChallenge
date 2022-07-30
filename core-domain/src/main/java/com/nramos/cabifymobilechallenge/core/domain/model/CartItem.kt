package com.nramos.cabifymobilechallenge.core.domain.model

data class CartItem(
    val product: Product,
    var quantity: Int,
    var totalPrice: Double,
    var totalPriceWithDiscounts: Double,
    var pricePerUnit: Double,
    var pricePerUnitWithDiscount: Double
)