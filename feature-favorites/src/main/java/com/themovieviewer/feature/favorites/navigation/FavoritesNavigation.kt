package com.themovieviewer.feature.favorites.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.themovieviewer.core.common.navigation.NavigationDestination
import com.themovieviewer.feature.favorites.FavoritesRoute

object FavoritesDestination : NavigationDestination {
    override val route = "favorites_route"
    override val destination = "favorites_destination"
}

fun NavGraphBuilder.favoritesGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = FavoritesDestination.route) {
        FavoritesRoute(windowSizeClass)
    }
}
