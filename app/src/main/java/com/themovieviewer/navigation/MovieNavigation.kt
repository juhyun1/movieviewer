package com.themovieviewer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.themovieviewer.feature.favorites.R.string.favorites
import com.themovieviewer.feature.favorites.navigation.FavoritesDestination
import com.themovieviewer.feature.movielist.R.string.movie
import com.themovieviewer.feature.movielist.navigation.MovieListDestination

class MovieNavigation(private val navController: NavHostController) {

    fun navigateTo(destination: Destination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}


data class Destination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

val DESTINATIONS = listOf(
    Destination(
        route = MovieListDestination.route,
        selectedIcon = Icons.Filled.Upcoming,
        unselectedIcon = Icons.Outlined.Upcoming,
        iconTextId = movie
    ),
    Destination(
        route = FavoritesDestination.route,
        selectedIcon = Icons.Filled.Upcoming,
        unselectedIcon = Icons.Outlined.Upcoming,
        iconTextId = favorites
    ),
)