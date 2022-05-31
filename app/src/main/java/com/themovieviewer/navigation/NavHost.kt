package com.themovieviewer.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.themovieviewer.feature.details.navigation.DetailsDestination
import com.themovieviewer.feature.details.navigation.detailsGraph
import com.themovieviewer.feature.favorites.navigation.favoritesGraph
import com.themovieviewer.feature.movielist.navigation.MovieListDestination
import com.themovieviewer.feature.movielist.navigation.movieListGraph

@Composable
fun NavHost(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MovieListDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        movieListGraph(windowSizeClass = windowSizeClass, navigateToDetails = { navController.navigate("${DetailsDestination.route}/$it") })
        favoritesGraph(windowSizeClass = windowSizeClass)
        detailsGraph(windowSizeClass = windowSizeClass)
    }
}