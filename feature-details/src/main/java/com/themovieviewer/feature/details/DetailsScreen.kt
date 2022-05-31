package com.themovieviewer.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber

@Composable
fun DetailsRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    movieId: Int
) {
    DetailsScreen(
        windowSizeClass = windowSizeClass,
        modifier = modifier,
        movieId = movieId
    )
}

@Composable
fun DetailsScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    movieId: Int,
    vm: DetailsViewModel = hiltViewModel()
) {

    Timber.d("Test : This Screen is Details Screen id : $movieId")

    LaunchedEffect(key1 = vm) {
        vm.getDetailsInfo(movieId = movieId)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "This Screen is Details Screen",
            color = Color.White,
            fontSize = 20.sp
        )
    }
}