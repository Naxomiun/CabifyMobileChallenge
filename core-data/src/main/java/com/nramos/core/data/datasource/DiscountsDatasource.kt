package com.nramos.core.data.datasource

import com.nramos.core.data.di.IoDispatcher
import com.nramos.core.data.mapper.DiscountsMapper
import com.nramos.core.data.model.DiscountResponse
import com.nramos.core.domain.model.Discount
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DiscountsDatasource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    //Function simulating backend call
    suspend fun getDiscounts(): List<Discount> =
        withContext(ioDispatcher) {
            DiscountsMapper.fromDiscountsResponseToModel(
                listOf(
                    DiscountResponse(
                        appliesTo = "VOUCHER",
                        discountType = "TWO_X_ONE"
                    ),
                    DiscountResponse(
                        appliesTo = "TSHIRT",
                        discountType = "BULK",
                        minQuantity = 3,
                        discountRate = 0.95,
                    )
                )
            )
        }

}