package com.themovieviewer.core.data.repository

import com.themovieviewer.core.database.model.FavoritesMovieVo

interface FavoritesMovieRepository {
    suspend fun getFavoritesMovies(id: Int): FavoritesMovieVo

    suspend fun deleteFavoritesMovie(favoritesMovie: FavoritesMovieVo)

    suspend fun insertFavoritesMovie(favoritesMovie: FavoritesMovieVo)
}