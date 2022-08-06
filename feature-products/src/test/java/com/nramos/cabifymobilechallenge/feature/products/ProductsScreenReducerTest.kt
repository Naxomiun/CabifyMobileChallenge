package com.nramos.cabifymobilechallenge.feature.products

import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class ProductsScreenReducerTest {

    @MockK
    lateinit var mockProducts: List<Product>

    @MockK
    lateinit var mockOrder: Order

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `fetch products started shows loading`() {
        val inputState = ProductsScreenState()
        val inputAction = ProductsScreenAction.FetchProductsStarted

        val reducer = ProductsScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assert(newState.productsLoading)
    }

    @Test
    fun `fetch products success`() {
        val inputState = ProductsScreenState()
        val inputAction = ProductsScreenAction.FetchProductsSucceed(mockProducts)

        val expectedState = inputState.copy(
            productsLoading = false,
            products = mockProducts
        )

        val reducer = ProductsScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assert(newState == expectedState)
    }

    @Test
    fun `item adding started and show spinner`() {
        val inputState = ProductsScreenState()
        val inputAction = ProductsScreenAction.ProductAddedToCartStarted

        val reducer = ProductsScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assert(newState.spinnerLoading)
    }

    @Test
    fun `item adding success`() {
        val inputState = ProductsScreenState()
        val inputAction = ProductsScreenAction.ProductAddedToCartSucceed(mockOrder)

        val expectedState = inputState.copy(
            spinnerLoading = false,
            order = mockOrder
        )

        val reducer = ProductsScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assert(newState == expectedState)
    }

    @Test
    fun `unsupported actions returns the same state`() {
        val inputState = ProductsScreenState()
        val inputAction = ProductsScreenAction.FetchProducts
        val reducer = ProductsScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assert(newState == inputState)
    }

}