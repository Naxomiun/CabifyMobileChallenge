# Cabify Mobile Challenge Solution


The developed application consists of two screens; the product screen and the cart screen.

**Product Screen:** shows every product fetched from backend, including price, promotion the possibility to add it to the cart. <br/>
**Cart Screen:** shows every item in your cart, with the promotion applied if any as well as the quantity, original and discounted prices and the original and discounted total.

# Documentation

The project tries to follow CLEAN and SOLID principles.
The selected MVx pattern this time is MVI.

## MVI

MVI used hand in hand with compose allows an easy use of unidirectional data flow (UDF) and a simple way to compose UI through a state machine as the unique source of truth.
This project uses Redux pattern to apply this MVI; this helps us to keep our classes and actions much cleaner, with less liabilities and easily testable. These patterns may not be useful in small projects like this one. The intention is to serve as an example of how what it can be achieved.

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

## Modules

The modularization per feature helps to maintain the code decoupled and easily navigable, helps to isolate bugs and improves parallel compilation in large codebases. Each module contains its own dependency injection and resources.

### App
Contains the App class & state and the single activity entry point together with the main navigation host and base theme & properties.

### Core-data
Contains services, network & cache datasources and repositories implementations along with their corresponding models and mappers.

### Core-domain #### (Optional layer)
Contains repository interfaces, use cases along with their corresponding models. This layer can be omitted if the domain logic is not commonly shared and can be delegated to repositories directly. In this case it is added only as an architectural demonstration.

### Core-navigation
Contains any logic related too Compose Navigation so that all presentation modules that require it can communicate with each other.

### Core-presentation
Contains common elements to presentation such as redux basis or shared UI components.

### Core-testing
Contains common elements to testing such mocked dependencies, modules or utilities.

### Feature-XXX
Contains everything related to the presentation of this feature and its resources

### Compose
This project has been entirely developed with compose. Although many developers are reluctant with this, it has been decided to use it as an example of new technology and how it adapts to our architecture.

## Testing
Some sample unit tests have been included in the project. These tests have been focused on Redux testing because it seems to me the most interesting thing to discuss when choosing an MVI pattern approach.. These tests They do not cover all the functionalities but serve as an example. MockK used for mocking purposes.

## Miscellaneous

`CartDatasource` stores the cart items in memory to simulate backend. The cart has not been persisted because it should be the backend the one that give us the order to avoid inconsistencies.<br/>
`DiscountsDatasource` simulates the backend to get the different discounts.<br/>

`CartRepository logic` cart repository is in charge of calculate the associated discounts oof the different products and quantities. Why not do it in the use case? Well, in this case, I assume that every time we fetch the cart, we need to get the discounts too. Therefore if we delegate this logic to a use case we run the risk that another developer does not know the existence of this use case and directly accesses the repository skipping the calculation of discounts or any other mandatory logic.<br/>
`ProductRepository logic` following the same logic explained above, every time we fetch the products, they have to come with their associated discount. 

In this challenge, domain layer has been included for example purposes but because of the size of the actual project it is irrelevant and redundant. No logic is shared or so complex so that use cases have to be used; repositories can handle this well. Likewise, when our interfaces only have a single implementation with 'impl' suffix, it may be a sign that we do not need an interface in that case. This 'useless' interfaces also serve as an example for a scalable project.
