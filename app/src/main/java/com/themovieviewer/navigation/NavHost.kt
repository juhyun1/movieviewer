package com.themovieviewer.navigation

import android.content.Intent
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.themovieviewer.feature.details.DetailsActivity
import com.themovieviewer.feature.details.navigation.DetailsDestination
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
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        movieListGraph(
            windowSizeClass = windowSizeClass,
            navigateToDetails = {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(DetailsDestination.detailsArg, it.toInt())
                context.startActivity(intent)
            }
        )
        favoritesGraph(
            windowSizeClass = windowSizeClass,
            navigateToDetails = {
            }
        )
    }
}
