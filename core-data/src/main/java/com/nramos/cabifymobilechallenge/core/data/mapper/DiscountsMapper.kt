package com.nramos.cabifymobilechallenge.core.data.mapper

import com.nramos.cabifymobilechallenge.core.data.model.DiscountResponse
import com.nramos.cabifymobilechallenge.core.domain.model.Discount
import com.nramos.cabifymobilechallenge.core.domain.model.DiscountType
import com.nramos.cabifymobilechallenge.core.domain.model.ProductType

object DiscountsMapper {

    fun fromDiscountsResponseToModel(discountResponse: List<DiscountResponse>): List<Discount> {
        return discountResponse.mapNotNull {
            val discountType = DiscountType.fromType(it.discountType)
            val appliesTo = ProductType.fromCode(it.appliesTo)
            when(discountType) {
                DiscountType.TWO_X_ONE -> Discount.TwoForOne(
                    appliesTo = appliesTo
                )
                DiscountType.BULK -> Discount.Bulk(
                    appliesTo = appliesTo,
                    minQuantity = it.quantity ?: 0,
                    discountRate = it.discountRate ?: 0.0,
                )
                else -> null
            }
        }
    }

}