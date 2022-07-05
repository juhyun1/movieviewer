package com.themovieviewer.core.data.repository

import com.themovieviewer.core.database.DatabaseDataSource
import com.themovieviewer.core.database.model.FavoritesMovieVo
import javax.inject.Inject

class FavoritesMovieRepositoryImpl @Inject constructor(
    private val dataSource: DatabaseDataSource
) : FavoritesMovieRepository {

    suspend fun getFavoritesMovies(id: Int): FavoritesMovieVo = dataSource.getFavoritesMovies(id)

    suspend fun deleteFavoritesMovie(favoritesMovie: FavoritesMovieVo) = dataSource.deleteFavoritesMovie(favoritesMovie = favoritesMovie)

    suspend fun insertFavoritesMovie(favoritesMovie: FavoritesMovieVo) = dataSource.insertFavoritesMovie(favoritesMovie = favoritesMovie)
}
