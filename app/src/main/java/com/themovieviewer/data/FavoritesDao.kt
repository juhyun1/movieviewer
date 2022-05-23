package com.themovieviewer.data

import androidx.room.Dao
import androidx.room.Query
import com.themovieviewer.core.data.BaseDao
import com.themovieviewer.core.data.vo.Favorites

@Dao
interface FavoritesDao : BaseDao<Favorites> {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): List<Favorites>
}
