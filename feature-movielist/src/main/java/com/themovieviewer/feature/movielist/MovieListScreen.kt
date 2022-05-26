package com.themovieviewer.feature.movielist

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.themovieviewer.core.ui.component.TopAppBar

@Composable
fun MovieListRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    MovieListScreen(
        windowSizeClass = windowSizeClass,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MovieListScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                titleRes = R.string.top_app_bar_preview_title,
                navigationIcon = Icons.Filled.Search,
                navigationIconContentDescription = "navigationIconContentDescription",
                actionIcon = Icons.Outlined.AccountCircle,
                actionIconContentDescription = "actionIconContentDescription",
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                )
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Text(text = "This is Just Test")
        }
    }
}
