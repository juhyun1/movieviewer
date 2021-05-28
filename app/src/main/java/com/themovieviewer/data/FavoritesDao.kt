package com.themovieviewer.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FavoritesDao : BaseDao<Favorites> {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): List<Favorites>
}