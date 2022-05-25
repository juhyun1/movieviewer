package com.themovieviewer.core.data

import androidx.room.Dao
import androidx.room.Query
import com.themovieviewer.core.model.data.vo.FavoritesVo

@Dao
interface FavoritesDao : BaseDao<FavoritesVo> {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): List<FavoritesVo>
}
