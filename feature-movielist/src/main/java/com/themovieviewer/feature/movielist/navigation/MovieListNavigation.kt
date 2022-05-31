package com.themovieviewer.feature.movielist.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.themovieviewer.core.common.navigation.NavigationDestination
import com.themovieviewer.feature.movielist.MovieListRoute

object MovieListDestination : NavigationDestination {
    override val route = "movie_list_route"
    override val destination = "movie_list_destination"
}

fun NavGraphBuilder.movieListGraph(
    windowSizeClass: WindowSizeClass,
    navigateToDetails: (String) -> Unit
) {
    composable(route = MovieListDestination.route) {
        MovieListRoute(windowSizeClass, navigateToDetails = navigateToDetails)
    }
}
