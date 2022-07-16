package com.themovieviewer.feature.movielist.usecase

import com.themovieviewer.core.data.mapper.asEntity
import com.themovieviewer.core.data.repository.FavoritesMovieRepository
import com.themovieviewer.core.model.data.Movie
import javax.inject.Inject

class InsertFavoriteUC @Inject constructor(
    private val repository: FavoritesMovieRepository
){
    suspend operator fun invoke(movie: Movie) {
        repository.insertFavoritesMovie(favoritesMovie = movie.asEntity())
    }
}