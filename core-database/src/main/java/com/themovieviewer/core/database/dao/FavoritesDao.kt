package com.themovieviewer.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.themovieviewer.core.database.model.FavoritesVo

@Dao
interface FavoritesDao : BaseDao<FavoritesVo> {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): List<FavoritesVo>
}
