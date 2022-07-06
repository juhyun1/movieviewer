package com.themovieviewer.usecase

import com.themovieviewer.core.data.repository.FavoritesMovieRepositoryImpl
import com.themovieviewer.core.model.usecase.DeleteFavoriteMovieUseCase
import javax.inject.Inject

class DeleteFavoriteMovieUseCaseImpl @Inject constructor(
    private val favoritesMovieRepository: FavoritesMovieRepositoryImpl
) : DeleteFavoriteMovieUseCase {

    suspend fun invoke(
        favorites: com.themovieviewer.core.model.data.Favorites,
    ) {
//        favoritesRepository.deleteFavorites(favorites)
//        favoritesMovieRepository.deleteFavoritesMovie(favoritesMovie)
    }
}
