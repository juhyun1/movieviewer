package com.themovieviewer.core.data.repository

import androidx.annotation.WorkerThread
import androidx.paging.PagingSource
import com.themovieviewer.core.model.data.vo.FavoritesMovieVo
import javax.inject.Inject

class FavoritesMovieRepository @Inject constructor(private val favoritesMovieDao: FavoritesMovieDao) {

    fun getFavoritesMovies(): PagingSource<Int, FavoritesMovieVo> = favoritesMovieDao.getFavoritesMovies()
    fun getFavoritesMovies(id: Int): FavoritesMovieVo = favoritesMovieDao.getFavoritesMovies(id)

    @WorkerThread
    suspend fun deleteFavoritesMovie(favoritesMovie: FavoritesMovieVo) = favoritesMovieDao.delete(favoritesMovie)

    @WorkerThread
    suspend fun insertFavoritesMovie(favoritesMovie: FavoritesMovieVo) = favoritesMovieDao.insert(favoritesMovie)
}
