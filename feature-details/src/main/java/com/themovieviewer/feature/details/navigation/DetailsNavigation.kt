package com.themovieviewer.feature.details.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.themovieviewer.core.common.navigation.NavigationDestination
import com.themovieviewer.feature.details.DetailsRoute

object DetailsDestination : NavigationDestination {
    override val route = "details_route"
    override val destination = "details_destination"
    const val detailsArg = "detailsId"
}

fun NavGraphBuilder.detailsGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(
        route = "${DetailsDestination.route}/{${DetailsDestination.detailsArg}}",
        arguments = listOf(
            navArgument(DetailsDestination.detailsArg) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val arg = backStackEntry.arguments?.getInt(DetailsDestination.detailsArg) ?: 0
        DetailsRoute(windowSizeClass, movieId = arg)
    }
}
