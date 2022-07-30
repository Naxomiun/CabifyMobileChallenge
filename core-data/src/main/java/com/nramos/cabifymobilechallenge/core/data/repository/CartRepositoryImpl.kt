package com.nramos.cabifymobilechallenge.core.data.repository

import com.nramos.cabifymobilechallenge.core.data.datasource.CartDatasource
import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Discount
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.domain.repository.CartRepository
import javax.inject.Inject
import kotlin.math.ceil

class CartRepositoryImpl @Inject constructor(
    private val cartDatasource: CartDatasource
) : CartRepository {

    override suspend fun addItemToCart(product: Product) {
        cartDatasource.addToCart(product)
    }

    override suspend fun removeItemFromCart(cartItem: CartItem) {
        cartDatasource.removeFromCart(cartItem)
    }

    override suspend fun getCartOrder(): Order {
        val order = cartDatasource.getOrder()
        order.items.forEach {
            calculateItemPrices(it)
        }
        calculateOrderPrice(order)
        return order
    }

    private fun calculateItemPrices(
        cartItem: CartItem
    ) {
        when (cartItem.product.discount) {
            is Discount.Bulk -> getBulkDiscountPrice(cartItem)
            is Discount.TwoForOne -> getTwoForOneDiscountPrice(cartItem)
            null -> getNoDiscountsPrice(cartItem)
        }
    }

    private fun getBulkDiscountPrice(cartItem: CartItem) {
        val discount = cartItem.product.discount as Discount.Bulk
        cartItem.pricePerUnitWithDiscount = if (cartItem.quantity >= discount.minQuantity) {
            cartItem.pricePerUnit * discount.discountRate
        } else {
            cartItem.pricePerUnit
        }
        cartItem.totalPriceWithDiscounts = cartItem.pricePerUnitWithDiscount * cartItem.quantity
        cartItem.totalPrice = cartItem.pricePerUnit * cartItem.quantity
    }

    private fun getTwoForOneDiscountPrice(cartItem: CartItem) {
        val itemsToCharge = ceil((cartItem.quantity / 2.toDouble()))
        cartItem.totalPriceWithDiscounts = itemsToCharge * cartItem.pricePerUnit
        cartItem.totalPrice = cartItem.quantity * cartItem.pricePerUnit
    }

    private fun getNoDiscountsPrice(cartItem: CartItem) {
        cartItem.pricePerUnitWithDiscount = cartItem.pricePerUnit
        cartItem.totalPriceWithDiscounts = cartItem.pricePerUnitWithDiscount * cartItem.quantity
        cartItem.totalPrice = cartItem.pricePerUnit * cartItem.quantity
    }

    private fun calculateOrderPrice(order: Order) {
        var totalPrice = 0.0
        var totalPriceWithDiscounts = 0.0

        order.items.forEach {
            totalPrice += it.totalPrice
            totalPriceWithDiscounts += it.totalPriceWithDiscounts
        }

        order.totalPrice = totalPrice
        order.totalPriceWithDiscounts = totalPriceWithDiscounts
    }

}