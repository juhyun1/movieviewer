package com.themovieviewer.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.themovieviewer.core.ui.R

@Composable
fun MovieInfoItem(movieId: Int, imageSrc: String, title: String, date: String, navigateToDetails: (String) -> Unit) {

    Column(modifier = Modifier
        .width(width = 150.dp)
        .clickable { navigateToDetails.invoke(movieId.toString()) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageSrc)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 150.dp, height = 230.dp)
                .clip(RoundedCornerShape(10.dp))
        )
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