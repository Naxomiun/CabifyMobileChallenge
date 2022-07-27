package com.nramos.core.domain.model

sealed class Discount {

    abstract val appliesTo: ProductType

    data class TwoForOne(
        override val appliesTo: ProductType
    ) : Discount()

    data class Bulk(
        override val appliesTo: ProductType,
        val minQuantity: Int,
        val discountRate: Double
    ) : Discount()
}

enum class DiscountType {
    NONE,
    TWO_X_ONE,
    BULK;

    companion object {
        fun fromType(type: String): DiscountType =
            values().associateBy { it.name }[type] ?: NONE
    }
}