package com.themovieviewer.feature.details.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.themovieviewer.core.common.navigation.NavigationDestination
import com.themovieviewer.core.model.data.Trailer
import com.themovieviewer.feature.details.DetailsRoute
import com.themovieviewer.feature.details.DetailsViewModel

object DetailsDestination : NavigationDestination {
    override val route = "details_route"
    override val destination = "details_destination"
    const val detailsArg = "detailsId"
}

fun NavGraphBuilder.detailsGraph(
    windowSizeClass: WindowSizeClass,
    onClickMovie: (Int) -> Unit,
    onClickPeople: (Int) -> Unit,
    onClickTrailer: (Trailer) -> Unit,
) {
    composable(
        route = "${DetailsDestination.route}/{${DetailsDestination.detailsArg}}",
        arguments = listOf(
            navArgument(DetailsDestination.detailsArg) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val vm: DetailsViewModel = hiltViewModel()
        val args = backStackEntry.arguments
        DetailsRoute(
            windowSizeClass = windowSizeClass,
            movieId = args?.getInt(DetailsDestination.detailsArg) ?: vm.movieID,
            onClickMovie = onClickMovie,
            onClickPeople = onClickPeople,
            onClickTrailer = onClickTrailer
        )
    }
}
