package com.themovieviewer.feature.movielist.usecase

import com.themovieviewer.core.data.repository.FavoritesMovieRepository
import javax.inject.Inject

class GetFavoriteUC @Inject constructor(
    private val repository: FavoritesMovieRepository
) {
    suspend operator fun invoke(movieId: Int): Boolean {
        return repository.getFavoritesMovies(id = movieId) != null
    }
}