package com.themovieviewer.feature.people.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.themovieviewer.core.common.navigation.NavigationDestination
import com.themovieviewer.feature.people.PeopleRoute
import com.themovieviewer.feature.people.PeopleViewModel
import timber.log.Timber

object PeopleDestination : NavigationDestination {
    override val route = "people_route"
    override val destination = "people_destination"
    const val peopleArg = "personId"
}

fun NavGraphBuilder.peopleGraph(
    windowSizeClass: WindowSizeClass,
    onClickMovie: (Int) -> Unit
) {
    composable(
        route = "${PeopleDestination.route}/{${PeopleDestination.peopleArg}}",
        arguments = listOf(
            navArgument(PeopleDestination.peopleArg) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        PeopleRoute(
            windowSizeClass,
            personId = backStackEntry.arguments?.getInt(PeopleDestination.peopleArg) ?: 0,
            onClickMovie = onClickMovie
        )
    }
}
