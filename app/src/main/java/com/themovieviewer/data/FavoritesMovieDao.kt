package com.themovieviewer.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesMovieDao : BaseDao<FavoritesMovie> {

    @Query("SELECT * FROM movies")
    fun getFavoritesMovies(): Flow<List<FavoritesMovie>>
}