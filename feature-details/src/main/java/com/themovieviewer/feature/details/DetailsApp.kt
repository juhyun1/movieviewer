package com.themovieviewer.feature.details

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.themovieviewer.core.common.navigation.Navigation
import com.themovieviewer.core.ui.theme.Theme
import com.themovieviewer.feature.details.navigation.DetailsDestination
import com.themovieviewer.feature.details.navigation.detailsGraph
import com.themovieviewer.feature.people.navigation.PeopleDestination
import com.themovieviewer.feature.people.navigation.peopleGraph

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DetailsApp(windowSizeClass: WindowSizeClass, movieId: Int) {
    Theme {
        Scaffold(
            modifier = Modifier,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                DetailsNavHost(
                    windowSizeClass = windowSizeClass,
                    movieId = movieId,
                    modifier = Modifier
                        .padding(padding)
                        .consumedWindowInsets(padding)
                )
            }
        }
    }
}

@Composable
fun DetailsNavHost(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    movieId: Int,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "dummy"
) {
    val navigation = remember(navController) {
        Navigation(navController)
    }
    val context = LocalContext.current as Activity
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable("dummy") {
            LaunchedEffect(key1 = true) {
                navigation.navigateTo(route = DetailsDestination.route, args = movieId.toString(), popup = true)
            }
        }
        detailsGraph(
            windowSizeClass = windowSizeClass,
            onClickMovie = {
                navigation.navigateTo(DetailsDestination.route, it.toString())
            },
            onClickPeople = {
                navigation.navigateTo(PeopleDestination.route, it.toString())
            },
            onClickTrailer = { item ->
                val intent = YouTubeStandalonePlayer.createVideoIntent(
                    context,
                    item.id,
                    item.key,
                    0,
                    true,
                    true
                )
                context.startActivity(intent)
            }
        )
        peopleGraph(
            windowSizeClass = windowSizeClass,
            onClickMovie = {
                navigation.navigateTo(DetailsDestination.route, it.toString())
            }
        )
    }
}