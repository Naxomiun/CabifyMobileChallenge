package com.nramos.core.data.mapper

import com.nramos.core.data.model.DiscountResponse
import com.nramos.core.domain.model.Discount
import com.nramos.core.domain.model.DiscountType
import com.nramos.core.domain.model.ProductType

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
                    minQuantity = it.minQuantity ?: 0,
                    discountRate = it.discountRate ?: 0.0,
                )
                else -> null
            }
        }
    }

}