package com.themovieviewer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Movie
import com.themovieviewer.core.common.navigation.Destination
import com.themovieviewer.feature.favorites.R.string.favorites
import com.themovieviewer.feature.movielist.R.string.movie
import com.themovieviewer.feature.favorites.navigation.FavoritesDestination
import com.themovieviewer.feature.movielist.navigation.MovieListDestination

val BOTTOM_BAR_DESTINATIONS = listOf(
    Destination(
        route = MovieListDestination.route,
        selectedIcon = Icons.Filled.Movie,
        unselectedIcon = Icons.Outlined.Movie,
        iconTextId = movie
    ),
    Destination(
        route = FavoritesDestination.route,
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.BookmarkBorder,
        iconTextId = favorites
    ),
)