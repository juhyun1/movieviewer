package com.themovieviewer.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesMovieDao : BaseDao<FavoritesMovie> {

    @Query("SELECT * FROM movies")
    fun getFavoritesMovies(): PagingSource<Int, FavoritesMovie>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getFavoritesMovies(id: Int): FavoritesMovie
}