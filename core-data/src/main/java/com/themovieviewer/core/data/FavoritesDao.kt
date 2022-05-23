package com.themovieviewer.core.data

import androidx.room.Dao
import androidx.room.Query
import com.themovieviewer.core.data.vo.Favorites

@Dao
interface FavoritesDao : BaseDao<Favorites> {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): List<Favorites>
}
