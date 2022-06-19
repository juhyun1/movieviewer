package com.themovieviewer.feature.movielist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Rectangle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import com.themovieviewer.core.ui.component.BottomSheetOptionItem
import com.themovieviewer.core.ui.component.HeightSpacer
import com.themovieviewer.core.ui.component.MovieInfoItem
import com.themovieviewer.core.ui.component.TopAppBar
import com.themovieviewer.core.ui.util.imagePath
import com.themovieviewer.feature.movielist.model.PreferenceState
import com.themovieviewer.feature.movielist.util.category
import kotlinx.coroutines.launch
import timber.log.Timber

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

@OptIn(
    ExperimentalMaterialApi::class
)
@Composable
fun MovieListScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetContent = {
            MovieBottomSheetPart(scaffoldState = scaffoldState)
        },
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        topBar = {
            MovieTopBarPart()
        },
        contentColor = Color.Transparent
    ) { innerPadding ->
        MovieContentsPart(modifier = modifier, innerPadding = innerPadding, navigateToDetails = navigateToDetails)
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

@OptIn(
    ExperimentalMaterialApi::class
)
@Composable
fun MovieBottomSheetPart(scaffoldState: BottomSheetScaffoldState) {
    val vm: MovieListViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
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
    Surface(
        color = Color.White
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(128.dp),
        ) {
            HeightSpacer(height = 20f)
            BottomSheetOptionItem(icon = Icons.Default.Rectangle, text = R.string.bottom_sheet_item_now_playing) {
                vm.onCategoryChanged(category = it.category())
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
            HeightSpacer(height = 20f)
            BottomSheetOptionItem(icon = Icons.Default.Star, text = R.string.bottom_sheet_item_upcoming) {
                vm.onCategoryChanged(category = it.category())
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
        }
    }
}

@Composable
fun MovieTopBarPart() {
    val vm: MovieListViewModel = hiltViewModel()
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
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieContentsPart(modifier: Modifier, innerPadding: PaddingValues, navigateToDetails: (String) -> Unit) {
    val vm: MovieListViewModel = hiltViewModel()
    val state by vm.pager

    val lazyPagingItems = state.flow.collectAsLazyPagingItems()

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