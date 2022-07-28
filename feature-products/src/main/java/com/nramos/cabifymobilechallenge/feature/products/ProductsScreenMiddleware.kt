package com.nramos.cabifymobilechallenge.feature.products

import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.domain.usecase.AddItemToCart
import com.nramos.cabifymobilechallenge.core.domain.usecase.GetProducts
import com.nramos.cabifymobilechallenge.core.presentation.redux.Middleware
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store
import kotlinx.coroutines.delay
import javax.inject.Inject

class ProductsScreenMiddleware @Inject constructor(
    private val getProducts: GetProducts,
    private val addItemToCart: AddItemToCart
) : Middleware<ProductsScreenState, ProductsScreenAction> {

    override suspend fun process(
        action: ProductsScreenAction,
        currentState: ProductsScreenState,
        store: Store<ProductsScreenState, ProductsScreenAction>
    ) {
        when (action) {
            is ProductsScreenAction.AddToCartButtonClicked -> {
                processAddItemToCarAction(action.product, store)
            }
            is ProductsScreenAction.FetchProducts -> {
                processFetchProductsAction(store)
            }
            else -> {}
        }
    }

    private suspend fun processFetchProductsAction(store: Store<ProductsScreenState, ProductsScreenAction>) {
        store.dispatch(ProductsScreenAction.FetchProductsStarted)
        val products = getProducts()
        store.dispatch(ProductsScreenAction.FetchProductsSucceed(products = products))
    }

    private suspend fun processAddItemToCarAction(product: Product, store: Store<ProductsScreenState, ProductsScreenAction>) {
        store.dispatch(ProductsScreenAction.ProductAddedToCartStarted)
        delay(1000) //Simulate network call
        val order = addItemToCart(product)
        store.dispatch(ProductsScreenAction.ProductAddedToCartSucceed(order = order))
    }

}
