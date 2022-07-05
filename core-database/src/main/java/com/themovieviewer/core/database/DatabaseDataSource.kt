package com.themovieviewer.core.database

import com.themovieviewer.core.database.dao.FavoritesMovieDao
import com.themovieviewer.core.database.model.FavoritesMovieVo
import javax.inject.Inject

class DatabaseDataSource @Inject constructor(
    private val favoritesMovieDao: FavoritesMovieDao,
) {

    fun getFavoritesMovies(id: Int): FavoritesMovieVo = favoritesMovieDao.getFavoritesMovies(id)

    suspend fun deleteFavoritesMovie(favoritesMovie: FavoritesMovieVo) = favoritesMovieDao.delete(favoritesMovie)

    suspend fun insertFavoritesMovie(favoritesMovie: FavoritesMovieVo) = favoritesMovieDao.insert(favoritesMovie)


}