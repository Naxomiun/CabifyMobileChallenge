package com.nramos.core.data.mapper

import com.nramos.core.data.model.DiscountResponse
import com.nramos.core.domain.model.Discount
import com.nramos.core.domain.model.DiscountType
import com.nramos.core.domain.model.ProductType

object DiscountsMapper {

    fun fromDiscountsResponseToModel(discountResponse: DiscountResponse): Discount? {
        val discountType = DiscountType.fromType(discountResponse.discountType)
        val appliesTo = ProductType.fromCode(discountResponse.appliesTo)
        return when(discountType) {
            DiscountType.TWO_X_ONE -> Discount.TwoForOne(
                appliesTo = appliesTo
            )
            DiscountType.BULK -> Discount.Bulk(
                appliesTo = appliesTo,
                minQuantity = discountResponse.minQuantity ?: 0,
                discountRate = discountResponse.discountRate ?: 0.0,
            )
            else -> null
        }
    }

}