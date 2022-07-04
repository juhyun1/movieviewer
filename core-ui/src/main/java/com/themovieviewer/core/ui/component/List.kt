package com.themovieviewer.core.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.ui.R
import com.themovieviewer.core.ui.util.imagePath
import com.themovieviewer.core.ui.util.score

@Composable
fun MovieInfoItem(movieId: Int, imageSrc: String, title: String, date: String, checkBookMark: suspend (Int) -> Boolean, onClickBookMark: (Int) -> Unit, navigateToDetails: (String) -> Unit) {

    var saved by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        saved = checkBookMark(movieId)
    }

    Column(modifier = Modifier
        .width(width = 150.dp)
        .clickable { navigateToDetails.invoke(movieId.toString()) }
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageSrc)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(width = 150.dp, height = 230.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Icon(modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .clickable {
                    saved = !saved
                    onClickBookMark(movieId)
                },
                imageVector = if (saved) Icons.Filled.BookmarkBorder else Icons.Filled.Bookmark,
                contentDescription = null,
                tint = Color.Red)
        }

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = title,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = date,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CastItem(personId: Int, imageSrc: String, name: String, character: String, onClickPeople: (Int) -> Unit,) {
    Card(modifier = Modifier
        .size(width = 150.dp, height = 280.dp)
        .clickable {
            onClickPeople.invoke(personId)
        }
        ,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.1f))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageSrc)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(width = 150.dp, height = 180.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = name,
            modifier = Modifier.padding(start = 10.dp),
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = character,
            modifier = Modifier.padding(start = 10.dp),
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun MovieInfoItemRow(movie: Movie, onClickMovie: (Int) -> Unit) {
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
            error = painterResource(R.drawable.placeholder),
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
            Text(modifier = Modifier
                .weight(weight = 1f)
                .wrapContentWidth(align = Alignment.End),
                text = movie.vote_average.score(),
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun BottomSheetOptionItem(icon: ImageVector, @StringRes text: Int, selected: Boolean,onClick: (Int) -> Unit ) {
    Surface(
        color = if (selected) Color.LightGray else Color.White
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp)
            .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp)
            .clickable {
                onClick(text)
            }
        ) {
            Icon(imageVector = icon, tint = Color.Black, contentDescription = null)
            WidthSpacer(width = 5f)
            Text(
                text = stringResource(id = text),
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}