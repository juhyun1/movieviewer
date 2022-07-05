package com.themovieviewer.feature.movielist.usecase

import com.themovieviewer.core.data.repository.FavoritesMovieRepositoryImpl
import javax.inject.Inject

class FavoriteUC @Inject constructor(
    repository: FavoritesMovieRepositoryImpl
){
    suspend operator fun invoke() {

    }
}