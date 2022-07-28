package com.nramos.core.domain.model

data class Product(
    val code: String,
    val type: ProductType,
    val name: String,
    val price: Double,
    val currencyCode: String,
    val mediaUrl: String,
    val discount: Discount? = null,
) {
    fun hasDiscount() = discount != null
}

enum class ProductType {
    UNKNOWN,
    VOUCHER,
    TSHIRT,
    MUG;

    companion object {
        fun fromCode(code: String): ProductType =
            values().associateBy { it.name }[code] ?: UNKNOWN
    }

}