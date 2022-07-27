package com.nramos.core.data.model

data class DiscountResponse(
    val code: String,
    val appliesTo: String,
    val discountType: String,
    val minQuantity: Double,
    val priceReduction: Double
)