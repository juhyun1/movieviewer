package com.themovieviewer.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.themovieviewer.core.database.model.FavoritesMovieVo

@Dao
interface FavoritesMovieDao : BaseDao<FavoritesMovieVo> {

    @Query("SELECT * FROM movies")
    fun getFavoritesMovies(): PagingSource<Int, FavoritesMovieVo>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getFavoritesMovies(id: Int): FavoritesMovieVo?
}
