package com.nramos.feature.products

import com.nramos.core.domain.model.Product

data class State (
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
)