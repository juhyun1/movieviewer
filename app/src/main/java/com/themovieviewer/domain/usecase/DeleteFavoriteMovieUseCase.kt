package com.themovieviewer.domain.usecase

import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository

class DeleteFavoriteMovieUseCase(
    private val favoritesRepository: FavoritesRepository,
    private val favoritesMovieRepository: FavoritesMovieRepository
): UseCase {

    suspend fun execute(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        favoritesRepository.deleteFavorites(favorites)
        favoritesMovieRepository.deleteFavoritesMovie(favoritesMovie)
    }
}
