package com.nramos.core.data.model

data class DiscountResponse(
    val discountType: String,
    val appliesTo: String,
    val minQuantity: Int? = null,
    val discountRate: Double? = null
)