package com.nramos.cabifymobilechallenge.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store
import com.nramos.cabifymobilechallenge.feature.products.ProductsScreenAction
import com.nramos.cabifymobilechallenge.feature.products.ProductsScreenMiddleware
import com.nramos.cabifymobilechallenge.feature.products.ProductsScreenReducer
import com.nramos.cabifymobilechallenge.feature.products.ProductsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(
    productsScreenMiddleware: ProductsScreenMiddleware,
) : ViewModel() {

    private val store = Store(
        initialState = ProductsScreenState(),
        reducer = ProductsScreenReducer(),
        middlewares = listOf(
            productsScreenMiddleware
        )
    )

    val viewState: StateFlow<ProductsScreenState> = store.state

    init {
        viewModelScope.launch {
            store.dispatch(ProductsScreenAction.FetchProducts)
        }
    }

    fun addItemToCart(product: Product) {
        viewModelScope.launch {
            store.dispatch(ProductsScreenAction.AddToCartButtonClicked(product))
        }
    }

}