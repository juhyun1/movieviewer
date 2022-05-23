package com.themovieviewer.core.data.repository

import androidx.annotation.WorkerThread
import androidx.paging.PagingSource
import com.themovieviewer.core.data.FavoritesMovieDao
import com.themovieviewer.core.data.vo.FavoritesMovie
import javax.inject.Inject

class FavoritesMovieRepository @Inject constructor(private val favoritesMovieDao: FavoritesMovieDao) {

    fun getFavoritesMovies(): PagingSource<Int, FavoritesMovie> = favoritesMovieDao.getFavoritesMovies()
    fun getFavoritesMovies(id: Int): FavoritesMovie = favoritesMovieDao.getFavoritesMovies(id)

    @WorkerThread
    suspend fun deleteFavoritesMovie(favoritesMovie: FavoritesMovie) = favoritesMovieDao.delete(favoritesMovie)

    @WorkerThread
    suspend fun insertFavoritesMovie(favoritesMovie: FavoritesMovie) = favoritesMovieDao.insert(favoritesMovie)
}
