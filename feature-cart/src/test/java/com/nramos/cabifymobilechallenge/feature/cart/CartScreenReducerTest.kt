package com.nramos.cabifymobilechallenge.feature.cart

import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class CartScreenReducerTest {

    @MockK
    lateinit var mockOrder: Order

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `fetch started shows loading`() {
        val inputState = CartScreenState()
        val inputAction = CartScreenAction.FetchOrderStarted

        val reducer = CartScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assert(newState.orderLoading)
    }

    @Test
    fun `fetch order success`() {
        val inputState = CartScreenState()
        val inputAction = CartScreenAction.FetchOrderSucceed(mockOrder)

        val expectedState = inputState.copy(
            orderLoading = false,
            order = mockOrder
        )

        val reducer = CartScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assert(newState == expectedState)
    }

    @Test
    fun `item removing started shows spinner`() {
        val inputState = CartScreenState()
        val inputAction = CartScreenAction.ItemRemovingStarted

        val reducer = CartScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assert(newState.spinnerLoading)
    }

    @Test
    fun `item removing success`() {
        val inputState = CartScreenState()
        val inputAction = CartScreenAction.ItemRemovingSucceed(mockOrder)

        val expectedState = inputState.copy(
            spinnerLoading = false,
            order = mockOrder
        )

        val reducer = CartScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assert(newState == expectedState)
    }

    @Test
    fun `unsupported actions returns the same state`() {
        val inputState = CartScreenState()
        val inputAction = CartScreenAction.FetchOrder
        val reducer = CartScreenReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assert(newState == inputState)
    }

}