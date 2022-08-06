package com.nramos.cabifymobilechallenge.core.testing

import com.nramos.cabifymobilechallenge.core.presentation.redux.Action
import com.nramos.cabifymobilechallenge.core.presentation.redux.Middleware
import com.nramos.cabifymobilechallenge.core.presentation.redux.State
import com.nramos.cabifymobilechallenge.core.presentation.redux.Store

/*
    Middleware for testing purposes. Stores every processed action.
 */
class RegisterActionMiddleware<S: State, A: Action> : Middleware<S, A> {

    private val actionRegister: MutableList<Action> = mutableListOf()

    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        registerAction(action)
    }

    private fun registerAction(action: Action) {
        actionRegister.add(action)
    }

    fun checkIfActionsWereRegistered(vararg action: Action) {
        action.forEach {
            assert(actionRegister.contains(it))
        }
    }

}