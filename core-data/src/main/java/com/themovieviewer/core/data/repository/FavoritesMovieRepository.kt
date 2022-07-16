package com.themovieviewer.core.data.repository

import androidx.paging.PagingSource
import com.themovieviewer.core.database.model.FavoritesMovieVo

interface FavoritesMovieRepository {
    fun getFavoritesMovies(): PagingSource<Int, FavoritesMovieVo>

    suspend fun getFavoritesMovies(id: Int): FavoritesMovieVo?

    suspend fun deleteFavoritesMovie(favoritesMovie: FavoritesMovieVo)

    suspend fun insertFavoritesMovie(favoritesMovie: FavoritesMovieVo)
}
