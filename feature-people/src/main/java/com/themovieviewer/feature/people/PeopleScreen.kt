package com.themovieviewer.feature.people

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.themovieviewer.core.ui.R
import com.themovieviewer.core.ui.component.HeightSpacer
import com.themovieviewer.core.ui.component.WidthSpacer
import com.themovieviewer.core.ui.util.imagePath
import timber.log.Timber

@Composable
fun PeopleRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    personId: Int
) {
    PeopleScreen(
        windowSizeClass = windowSizeClass,
        modifier = modifier,
        personId = personId
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PeopleScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    personId: Int,

) {
    Timber.d("Test : backStackEntry")
    val vm: PeopleViewModel = hiltViewModel()
    Timber.d("Test : vm")
    val state by vm.peopleDetail.observeAsState()
    LaunchedEffect(key1 = vm) {
        vm.getPeopleInfo(personId = personId)
    }
    Scaffold(
        topBar = {}
    ) {
        state?.let {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp)
                    .verticalScroll(state = rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.profileImage?.imagePath())
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
                            text = it.name,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleLarge
                        )

                        HeightSpacer(height = 10f)
                        Text(
                            text = "Personal Info",
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium
                        )
                        HeightSpacer(height = 10f)
                        Text(
                            text = "Known For",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = it.knownFor,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        HeightSpacer(height = 10f)
                        Text(
                            text = "Birthday",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = it.birthday ?: "",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        HeightSpacer(height = 10f)
                        Text(
                            text = "Place of Birth",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = it.placeOfBirths ?: "",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        HeightSpacer(height = 10f)
                        Text(
                            text = "Place of Birth",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = it.placeOfBirths ?: "",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                HeightSpacer(height = 10f)
                Text(
                    text = "Biography",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
                HeightSpacer(height = 5f)
                Text(
                    text = it.biography,
                    color = Color.Black,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
