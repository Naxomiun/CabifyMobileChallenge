package com.nramos.core.domain.model

data class Order(
    val items: MutableList<CartItem>
) {

    fun getTotalItemsInOrder(): Int {
        var totalItems = 0
        items.forEach {
            totalItems += it.quantity ?: 0
        }
        return totalItems
    }


}