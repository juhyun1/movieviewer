package com.themovieviewer.usecase

import com.themovieviewer.core.data.repository.FavoritesMovieRepository
import com.themovieviewer.core.data.repository.FavoritesRepository
import com.themovieviewer.core.model.data.vo.FavoritesMovieVo
import com.themovieviewer.core.model.usecase.DeleteFavoriteMovieUseCase
import javax.inject.Inject

class DeleteFavoriteMovieUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val favoritesMovieRepository: FavoritesMovieRepository
) : DeleteFavoriteMovieUseCase {

    override suspend fun invoke(
        favorites: com.themovieviewer.core.model.data.Favorites,
        favoritesMovie: FavoritesMovieVo
    ) {
        favoritesRepository.deleteFavorites(favorites)
        favoritesMovieRepository.deleteFavoritesMovie(favoritesMovie)
    }

}