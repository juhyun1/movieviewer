package com.themovieviewer.core.data.repository

import androidx.paging.PagingSource
import com.themovieviewer.core.database.DatabaseDataSource
import com.themovieviewer.core.database.model.FavoritesMovieVo
import javax.inject.Inject

class FavoritesMovieRepositoryImpl @Inject constructor(
    private val dataSource: DatabaseDataSource
) : FavoritesMovieRepository {
    override fun getFavoritesMovies(): PagingSource<Int, FavoritesMovieVo> = dataSource.getFavoritesMovies()

    override suspend fun getFavoritesMovies(id: Int): FavoritesMovieVo? = dataSource.getFavoritesMovies(id)

    override suspend fun deleteFavoritesMovie(favoritesMovie: FavoritesMovieVo) = dataSource.deleteFavoritesMovie(favoritesMovie = favoritesMovie)

    override suspend fun insertFavoritesMovie(favoritesMovie: FavoritesMovieVo) = dataSource.insertFavoritesMovie(favoritesMovie = favoritesMovie)
}
