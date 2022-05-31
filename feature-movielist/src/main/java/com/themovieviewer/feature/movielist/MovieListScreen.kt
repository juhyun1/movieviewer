package com.themovieviewer.feature.movielist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.ui.component.MovieInfoItem
import com.themovieviewer.core.ui.component.TopAppBar
import com.themovieviewer.core.ui.util.imagePath
import com.themovieviewer.feature.movielist.model.PreferenceState
import kotlinx.coroutines.launch

@Composable
fun MovieListRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit
) {
    MovieListScreen(
        windowSizeClass = windowSizeClass,
        modifier = modifier,
        navigateToDetails = navigateToDetails
    )
}

@OptIn(ExperimentalLayoutApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MovieListScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit,
    vm: MovieListViewModel = hiltViewModel()
) {

    val pager = remember {
        Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 1000
            )
        ) { vm.getNowPlayingDataSource() }
    }

    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetContent = {
            val state by vm.preferenceState
            DisposableEffect(key1 = scaffoldState.bottomSheetState.isCollapsed) {
                onDispose {
                    if (scaffoldState.bottomSheetState.isCollapsed) {
                        vm.onPreferencesStateChange(state = PreferenceState.Hide)
                    }
                }
            }

            LaunchedEffect(key1 = state) {
                when(state) {
                    PreferenceState.Show -> {
                        scaffoldState.bottomSheetState.expand()
                    }
                    else -> {}
                }
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Swipe up to expand sheet")
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sheet content")
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        scope.launch { scaffoldState.bottomSheetState.collapse() }
                    }
                ) {
                    Text("Click to collapse sheet")
                }
            }
        },
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
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
                ),
                onActionClick = { vm.onPreferencesStateChange(state = PreferenceState.Show)}
            )
        },
        contentColor = Color.Transparent
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            LazyColumn {
                itemsIndexed(lazyPagingItems) { index, item ->

                    if (index % 2 == 0) {
                        MovieColumnItem(movie1 = item, movie2 = if (lazyPagingItems.itemCount > index + 1) lazyPagingItems[index + 1] else null, navigateToDetails = navigateToDetails)
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
}

@Composable
fun MovieColumnItem(movie1: Movie?, movie2: Movie?, navigateToDetails: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        movie1?.let {
            MovieInfoItem(movieId = movie1.id, imageSrc = it.poster_path?.imagePath() ?: "", title = it.title ?: "", date = it.release_date ?: "", navigateToDetails = navigateToDetails)
        }
        Spacer(modifier = Modifier.width(30.dp))
        movie2?.let{
            MovieInfoItem(movieId = movie2.id, imageSrc = it.poster_path?.imagePath() ?: "", title = it.title ?: "", date = it.release_date ?: "", navigateToDetails = navigateToDetails)
        }
    }
}
