package com.nramos.cabifymobilechallenge.feature.products

import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.presentation.redux.State

data class ProductsScreenState(
    val productsLoading: Boolean = false,
    val spinnerLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val order: Order? = null,
    val errorMessage: String? = null
) : State