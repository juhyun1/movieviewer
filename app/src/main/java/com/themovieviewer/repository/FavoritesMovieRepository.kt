package com.themovieviewer.repository

import androidx.annotation.WorkerThread
import com.themovieviewer.data.FavoritesMovie
import com.themovieviewer.data.FavoritesMovieDao
import javax.inject.Inject

class FavoritesMovieRepository @Inject constructor(private val favoritesMovieDao: FavoritesMovieDao) {

    fun getFavoritesMovies() = favoritesMovieDao.getFavoritesMovies()

    @WorkerThread
    suspend fun deleteFavoritesMovie(favoritesMovie: FavoritesMovie) = favoritesMovieDao.delete(favoritesMovie)

    @WorkerThread
    suspend fun insertFavoritesMovie(favoritesMovie: FavoritesMovie) = favoritesMovieDao.insert(favoritesMovie)
}