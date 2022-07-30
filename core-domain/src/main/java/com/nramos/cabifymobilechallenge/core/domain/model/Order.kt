package com.nramos.cabifymobilechallenge.core.domain.model

data class Order(
    var totalPrice: Double,
    var totalPriceWithDiscounts: Double,
    val items: MutableList<CartItem>
) {

    fun getTotalItemsInOrder(): Int {
        var totalItems = 0
        items.forEach {
            totalItems += it.quantity
        }
        return totalItems
    }

    fun hasDiscounts(): Boolean {
        return totalPrice != totalPriceWithDiscounts
    }

    fun getOrderCurrencyCode(): String {
        return items.firstOrNull()?.product?.currencyCode ?: ""
    }

}