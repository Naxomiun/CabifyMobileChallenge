# Cabify Mobile Challenge Solution


The developed application consists of two screens; the product screen and the cart screen.

**Product Screen** shows every product fetched from backend, including price, promotion the possibility to add it to the cart.
**Cart Screen** shows every item in your cart, with the promotion applied if any as well as the quantity, original and discounted prices and the original and discounted total.

# Documentation

The project tries to follow CLEAN and SOLID principles.
The selected MVx pattern this time is MVI.

## MVI

MVI used hand in hand with compose allows an easy use of unidirectional data flow (UDF) and a simple way to compose UI through a state machine as the unique source of truth.
This project uses Redux pattern to apply this MVI.

### How it works?

#### - States

```kotlin
interface State
````

Our state machine used to represent UI state. Every custom state implements this interface.

#### - Actions

```kotlin
interface Action
````

Represent actions derived from user interactions and changes made by our domain logic.

#### - Reducers

```kotlin
interface Reducer<S: State, A: Action> {
    fun reduce(currentState: S, action: A): S
}
````

Given a current state and an action is the responsible for transforming and updating the state for each action.

#### - Middlewares

```kotlin
interface Middleware<S: State, A: Action> {
    suspend fun process(action: A, currentState: S, store: Store<S, A>)
}
````
Given a current state,an action and a store is the responsible for executing business logic and dispatching actions accordingly.

#### - Stores

```kotlin
class Store<S: State, A: Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>> = emptyList()
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    private val currentState: S
        get() = _state.value

    suspend fun dispatch(action: A) {
        middlewares.forEach {
            it.process(action, currentState, this)
        }

        val newState = reducer.reduce(currentState, action)
        _state.value = newState
    }

}
````

Store is in charge of dispatching actions through every associated middleware and reducing the current state. It holds the reference to our states.

### Modules

The modularization per feature helps to maintain the code decoupled and easily navigable, helps to isolate bugs and improves parallel compilation in large codebases.

