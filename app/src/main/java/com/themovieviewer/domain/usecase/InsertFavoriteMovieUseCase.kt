package com.themovieviewer.domain.usecase

import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository

class InsertFavoriteMovieUseCase(
    private val favoritesRepository: FavoritesRepository,
    private val favoritesMovieRepository: FavoritesMovieRepository
): UseCase {

    suspend fun execute(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        favoritesRepository.insertFavorites(favorites)
        favoritesMovieRepository.insertFavoritesMovie(favoritesMovie)
    }
}
