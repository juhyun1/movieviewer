package com.themovieviewer.feature.details

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.data.Trailer
import com.themovieviewer.core.ui.R
import com.themovieviewer.core.ui.component.CastItem
import com.themovieviewer.core.ui.component.HeightSpacer
import com.themovieviewer.core.ui.component.WidthSpacer
import com.themovieviewer.core.ui.util.imagePath
import com.themovieviewer.core.ui.util.score
import com.themovieviewer.core.ui.util.thumbnailPath
import timber.log.Timber

@Composable
fun DetailsRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    movieId: Int,
    onClickMovie: (Int) -> Unit,
    onClickPeople: (Int) -> Unit,
) {
    DetailsScreen(
        windowSizeClass = windowSizeClass,
        movieId = movieId,
        onClickMovie = onClickMovie,
        onClickPeople = onClickPeople,
        modifier = modifier,
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    movieId: Int,
    onClickMovie: (Int) -> Unit,
    onClickPeople: (Int) -> Unit,
    vm: DetailsViewModel = hiltViewModel()
) {
    val state by vm.movieDetail.observeAsState()
    LaunchedEffect(key1 = vm) {
        vm.getDetailsInfo(movieId = movieId)
    }

    Scaffold(
        topBar = {}
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            state?.let {
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(it.backdropImage?.imagePath())
//                    .crossfade(true)
//                    .build(),
//                placeholder = painterResource(R.drawable.placeholder),
//                contentDescription = null,
//                contentScale = ContentScale.FillWidth,
//                alpha = 0.5f,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(10.dp))
//            )
                HeightSpacer(height = 20f)
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.posterImage?.imagePath())
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(width = 150.dp, height = 230.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    WidthSpacer(width = 10f)
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.title,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = it.releaseDate,
                            color = Color.Black,
                            style = MaterialTheme.typography.labelMedium
                        )
                        HeightSpacer(height = 20f)
                        Text(
                            text = it.tagline ?: "",
                            color = Color.Black,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                HeightSpacer(height = 30f)
                Text(
                    text = "Overview",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                HeightSpacer(height = 10f)
                Text(
                    text = it.overview ?: "",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                HeightSpacer(height = 30f)
                Text(
                    text = "Top Billed Cast",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                HeightSpacer(height = 10f)
                CreditsList(onClickPeople = onClickPeople)
                HeightSpacer(height = 30f)
                Text(
                    text = "Videos",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                HeightSpacer(height = 10f)
                TrailerList()
                HeightSpacer(height = 30f)
                Text(
                    text = "Recommendations",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                HeightSpacer(height = 10f)
                RecommendationsItemList( onClickMovie = onClickMovie )
            }
            HeightSpacer(height = 50f)
        }
    }
}

@Composable
fun CreditsList(onClickPeople: (Int) -> Unit,) {
    val vm: DetailsViewModel = hiltViewModel()
    val pager = remember {
        Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = true,
                maxSize = 3
            )
        ) { vm.getCreditsDataSource() }
    }

    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()

    LazyRow {
        itemsIndexed(lazyPagingItems) { index, item ->
            CastItem(personId = item?.id ?: 0, imageSrc = item?.profile_path?.imagePath() ?: "", name = item?.name ?: "", character = item?.character ?: "", onClickPeople = onClickPeople)
            WidthSpacer(width = 10f)
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

@Composable
fun TrailerList() {
    val vm: DetailsViewModel = hiltViewModel()
    val pager = remember {
        Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = true,
                maxSize = 3
            )
        ) { vm.getTrailerDataSource() }
    }

    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()
    val context = LocalContext.current

    LazyRow {
        itemsIndexed(lazyPagingItems) { index, item ->
            Box(
                contentAlignment = Alignment.Center,
            ) {
                item?.let {
                    TrailerItem(trailer = item, onClick = {
                        val intent = YouTubeStandalonePlayer.createVideoIntent(
                            context as Activity?,
                            item.id,
                            item.key,
                            0,
                            true,
                            true
                        )
                        context.startActivity(intent)
                    })
                }
            }
            WidthSpacer(width = 10f)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailerItem(trailer: Trailer, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(trailer.key.thumbnailPath())
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .heightIn(max = 180.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onClick.invoke()
                }
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.ic_trailer_payler)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun RecommendationsItemList(onClickMovie: (Int) -> Unit) {
    val vm: DetailsViewModel = hiltViewModel()
    val pager = remember {
        Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = true,
                maxSize = 3
            )
        ) { vm.getRecommendationsDataSource() }
    }

    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()

    LazyRow {
        itemsIndexed(lazyPagingItems) { index, item ->
            item?.let {
                RecommendationsItem(movie = item, onClickMovie = onClickMovie)
            }
            WidthSpacer(width = 10f)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationsItem(movie: Movie, onClickMovie: (Int) -> Unit) {
    Column(modifier = Modifier
        .width(width = 250.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.backdrop_path?.imagePath())
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(width = 250.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onClickMovie.invoke(movie.id)
                }
        )
        HeightSpacer(height = 5f)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.widthIn(max = 180.dp),
                text = movie.title ?: "",
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
            Text(modifier = Modifier.weight(weight = 1f).wrapContentWidth(align = Alignment.End),
                text = movie.vote_average.score(),
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
