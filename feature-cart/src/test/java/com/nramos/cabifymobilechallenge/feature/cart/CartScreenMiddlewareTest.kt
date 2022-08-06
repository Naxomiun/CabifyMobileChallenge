package com.nramos.cabifymobilechallenge.feature.cart

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.domain.usecase.GetCartOrderUseCase
import com.nramos.cabifymobilechallenge.core.domain.usecase.RemoveItemFromCartUseCase
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store
import com.nramos.cabifymobilechallenge.core.testing.RegisterActionMiddleware
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CartScreenMiddlewareTest {

    lateinit var testMiddleware: CartScreenMiddleware

    @MockK
    lateinit var getCartOrderUseCase: GetCartOrderUseCase

    @MockK
    lateinit var removeItemFromCartUseCase: RemoveItemFromCartUseCase

    @MockK
    lateinit var mockOrder: Order

    @MockK
    lateinit var mockCartItem: CartItem

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testMiddleware = CartScreenMiddleware(getCartOrderUseCase, removeItemFromCartUseCase)
    }

    @Test
    fun `fetch order and successs`() {
        coEvery { getCartOrderUseCase() }.returns(mockOrder)

        val inputState = CartScreenState()
        val inputAction = CartScreenAction.FetchOrder

        val registerActionsMiddleware = RegisterActionMiddleware<CartScreenState, CartScreenAction>()

        val store = Store(inputState, CartScreenReducer(), listOf(registerActionsMiddleware))

        val orderResult = runBlocking {
            getCartOrderUseCase()
        }

        runBlocking {
            testMiddleware.process(inputAction, inputState, store)
        }

        registerActionsMiddleware.checkIfActionsWereRegistered(
            CartScreenAction.FetchOrderStarted,
            CartScreenAction.FetchOrderSucceed(orderResult)
        )
    }

    @Test
    fun `remove item clicked and successs`() {
        coEvery { removeItemFromCartUseCase(mockCartItem) }.returns(mockOrder)

        val inputState = CartScreenState()
        val inputAction = CartScreenAction.RemoveItemClicked(mockCartItem)

        val registerActionsMiddleware = RegisterActionMiddleware<CartScreenState, CartScreenAction>()

        val store = Store(inputState, CartScreenReducer(), listOf(registerActionsMiddleware))

        val orderResult = runBlocking {
            removeItemFromCartUseCase(mockCartItem)
        }

        runBlocking {
            testMiddleware.process(inputAction, inputState, store)
        }

        registerActionsMiddleware.checkIfActionsWereRegistered(
            CartScreenAction.ItemRemovingStarted,
            CartScreenAction.ItemRemovingSucceed(orderResult)
        )
    }

}