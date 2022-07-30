package com.nramos.cabifymobilechallenge.core.data.model

data class DiscountResponse(
    val discountType: String,
    val appliesTo: String,
    val quantity: Int? = null,
    val discountRate: Double? = null
)