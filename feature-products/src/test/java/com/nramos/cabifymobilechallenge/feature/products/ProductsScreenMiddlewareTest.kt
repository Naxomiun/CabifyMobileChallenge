package com.nramos.cabifymobilechallenge.feature.products

import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.domain.usecase.AddItemToCartUseCase
import com.nramos.cabifymobilechallenge.core.domain.usecase.GetProductsUseCase
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store
import com.nramos.cabifymobilechallenge.core.testing.RegisterActionMiddleware
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductsScreenMiddlewareTest {

    lateinit var testMiddleware: ProductsScreenMiddleware

    @MockK
    lateinit var getProductsUseCase: GetProductsUseCase

    @MockK
    lateinit var addItemToCartUseCase: AddItemToCartUseCase

    @MockK
    lateinit var mockProducts: List<Product>

    @MockK
    lateinit var mockOrder: Order

    @MockK
    lateinit var mockProduct: Product

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testMiddleware = ProductsScreenMiddleware(getProductsUseCase, addItemToCartUseCase)
    }

    @Test
    fun `fetch products and successs`() {
        coEvery { getProductsUseCase() }.returns(mockProducts)

        val inputState = ProductsScreenState()
        val inputAction = ProductsScreenAction.FetchProducts

        val registerActionsMiddleware = RegisterActionMiddleware<ProductsScreenState, ProductsScreenAction>()

        val store = Store(inputState, ProductsScreenReducer(), listOf(registerActionsMiddleware))

        val productsResult = runBlocking {
            getProductsUseCase()
        }

        runBlocking {
            testMiddleware.process(inputAction, inputState, store)
        }

        registerActionsMiddleware.checkIfActionsWereRegistered(
            ProductsScreenAction.FetchProductsStarted,
            ProductsScreenAction.FetchProductsSucceed(productsResult)
        )
    }

    @Test
    fun `add item clicked and successs`() {
        coEvery { addItemToCartUseCase(mockProduct) }.returns(mockOrder)

        val inputState = ProductsScreenState()
        val inputAction = ProductsScreenAction.AddToCartButtonClicked(mockProduct)

        val registerActionsMiddleware = RegisterActionMiddleware<ProductsScreenState, ProductsScreenAction>()

        val store = Store(inputState, ProductsScreenReducer(), listOf(registerActionsMiddleware))

        val orderResult = runBlocking {
            addItemToCartUseCase(mockProduct)
        }

        runBlocking {
            testMiddleware.process(inputAction, inputState, store)
        }

        registerActionsMiddleware.checkIfActionsWereRegistered(
            ProductsScreenAction.ProductAddedToCartStarted,
            ProductsScreenAction.ProductAddedToCartSucceed(orderResult)
        )

    }

}