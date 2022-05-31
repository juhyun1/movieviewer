package com.themovieviewer.feature.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.themovieviewer.core.ui.R
import com.themovieviewer.core.ui.component.CastItem
import com.themovieviewer.core.ui.component.HeightSpacer
import com.themovieviewer.core.ui.component.WidthSpacer
import com.themovieviewer.core.ui.util.imagePath
import timber.log.Timber

@Composable
fun DetailsRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    DetailsScreen(
        windowSizeClass = windowSizeClass,
        modifier = modifier,
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    vm: DetailsViewModel = hiltViewModel()
) {
    val state by vm.movieDetail.observeAsState()
    Timber.d("Test : This Screen is Details Screen state : $state")

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
                CreditsList()
            }
            HeightSpacer(height = 50f)
        }
    }
}

@Composable
fun CreditsList() {
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
            CastItem(imageSrc = item?.profile_path?.imagePath() ?: "", name = item?.name ?: "", character = item?.character ?: "")
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