package com.nramos.cabifymobilechallenge.core.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    internal val destination: Destination,
    private val navArgs: List<NavArg> = emptyList()
) {

    class ContentType(feature: Destination) : NavCommand(feature)

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(destination.route)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId("itemId", NavType.IntType)
}