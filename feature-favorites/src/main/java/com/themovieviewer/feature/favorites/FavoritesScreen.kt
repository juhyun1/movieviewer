package com.themovieviewer.feature.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.ui.component.MovieColumnItem
import com.themovieviewer.core.ui.component.TopAppBar
import com.themovieviewer.feature.favorites.R.string.top_app_bar_title_favorites

@Composable
fun FavoritesRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit
) {
    FavoritesScreen(
        windowSizeClass = windowSizeClass,
        modifier = modifier,
        navigateToDetails = navigateToDetails
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FavoritesScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                titleRes = top_app_bar_title_favorites,
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
            FavoritesContentsPart(modifier = modifier, innerPadding = innerPadding, navigateToDetails = navigateToDetails)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FavoritesContentsPart(modifier: Modifier, innerPadding: PaddingValues, navigateToDetails: (String) -> Unit) {
    val vm: FavoritesViewModel = hiltViewModel()
    val lazyPagingItems = vm.pager.collectAsLazyPagingItems()
    val onClickBookMark: (Movie) -> Unit = {}//vm::onClickBookMark

    BoxWithConstraints(
        modifier = modifier
            .padding(innerPadding)
            .consumedWindowInsets(innerPadding)
    ) {
        LazyColumn {
            itemsIndexed(lazyPagingItems) { index, item ->
                if (index % 2 == 0) {
                    MovieColumnItem(
                        movie1 = item,
                        movie2 = if (lazyPagingItems.itemCount > index + 1) lazyPagingItems[index + 1] else null,
                        navigateToDetails = navigateToDetails,
                        onClickBookMark = onClickBookMark,
                        checkBookMark = { true }//checkBookMark
                    )
                }
            }

            if (lazyPagingItems.loadState.append == LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
