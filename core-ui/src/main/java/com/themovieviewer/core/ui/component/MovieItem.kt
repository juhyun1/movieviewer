package com.themovieviewer.core.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.themovieviewer.core.model.data.Movie

@Composable
fun MovieColumnItem(movie1: Movie?, movie2: Movie?, checkBookMark: suspend (Int) -> Boolean, onClickBookMark: (Movie) -> Unit, navigateToDetails: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        movie1?.let {
            MovieInfoItem(movie = movie1, checkBookMark = checkBookMark, onClickBookMark = onClickBookMark, navigateToDetails = navigateToDetails)
        }
        Spacer(modifier = Modifier.width(30.dp))
        movie2?.let{
            MovieInfoItem(movie = movie2,  checkBookMark = checkBookMark, onClickBookMark = onClickBookMark, navigateToDetails = navigateToDetails)
        }
    }
}