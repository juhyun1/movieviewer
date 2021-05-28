package com.themovieviewer.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao : BaseDao<Favorites> {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<Favorites>>
}