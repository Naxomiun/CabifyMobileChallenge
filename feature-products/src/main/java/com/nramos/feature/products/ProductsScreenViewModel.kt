package com.nramos.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.core.domain.model.Product
import com.nramos.core.domain.usecase.GetProducts
import com.nramos.core.presentation.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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