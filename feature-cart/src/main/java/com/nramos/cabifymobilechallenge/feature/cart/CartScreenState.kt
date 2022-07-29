package com.nramos.cabifymobilechallenge.feature.cart

import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.presentation.redux.State

data class CartScreenState(
    val orderLoading: Boolean = false,
    val spinnerLoading: Boolean = false,
    val order: Order? = null,
    val errorMessage: String? = null
) : State