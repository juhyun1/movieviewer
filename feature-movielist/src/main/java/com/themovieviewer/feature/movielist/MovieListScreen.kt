package com.themovieviewer.feature.movielist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.themovieviewer.core.datastore.Category
import com.themovieviewer.core.datastore.Language
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.ui.component.*
import com.themovieviewer.core.ui.theme.Theme
import com.themovieviewer.core.ui.util.imagePath
import com.themovieviewer.feature.movielist.model.PreferenceState
import com.themovieviewer.feature.movielist.util.category
import com.themovieviewer.feature.movielist.util.language
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
        sheetBackgroundColor = MaterialTheme.colorScheme.onBackground,
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
    val categorySelected = vm.categoryState
    val languageSelected = vm.languageState

    Surface(
        color = Color.White
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(450.dp),
        ) {
            HeightSpacer(height = 20f)
            Text(modifier = Modifier.padding(start = 30.dp),
                text = stringResource(id = R.string.bottom_sheet_item_category),
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )
            HeightSpacer(height = 20f)

            BottomSheetOptionItem(icon = Icons.Default.Diamond, selected = categorySelected.value == Category.NowPlaying, text = R.string.bottom_sheet_item_now_playing) {
                vm.onCategoryChanged(category = it.category())
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
            BottomSheetOptionItem(icon = Icons.Default.Star, selected = categorySelected.value == Category.Upcoming, text = R.string.bottom_sheet_item_upcoming) {
                vm.onCategoryChanged(category = it.category())
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
            BottomSheetOptionItem(icon = Icons.Default.Train, selected = categorySelected.value == Category.Popular, text = R.string.bottom_sheet_item_popular) {
                vm.onCategoryChanged(category = it.category())
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
            BottomSheetOptionItem(icon = Icons.Default.MonitorHeart, selected = categorySelected.value == Category.TopRate, text = R.string.bottom_sheet_item_top_rate) {
                vm.onCategoryChanged(category = it.category())
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }

            HeightSpacer(height = 20f)
            Text(modifier = Modifier.padding(start = 30.dp),
                text = stringResource(id = R.string.bottom_sheet_item_language),
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )
            HeightSpacer(height = 20f)
            BottomSheetOptionItem(icon = Icons.Default.Train, selected = languageSelected.value == Language.English, text = R.string.bottom_sheet_item_english) {
                vm.onLanguageChanged(language = it.language())
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
            BottomSheetOptionItem(icon = Icons.Default.MonitorHeart, selected = languageSelected.value == Language.Korean, text = R.string.bottom_sheet_item_korean) {
                vm.onLanguageChanged(language = it.language())
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
    val title by vm.titleState

    TopAppBar(
        titleRes = title,
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
    val onClickBookMark: (Movie) -> Unit = vm::onClickBookMark
    val checkBookMark: suspend (Int) -> Boolean = vm::checkBookMark

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
                        checkBookMark = checkBookMark
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