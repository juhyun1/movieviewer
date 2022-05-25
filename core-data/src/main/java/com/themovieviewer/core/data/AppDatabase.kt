package com.themovieviewer.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.themovieviewer.core.data.util.Converters
import com.themovieviewer.core.model.data.vo.FavoritesVo
import com.themovieviewer.core.model.data.vo.FavoritesMovieVo

@TypeConverters(Converters::class)
@Database(entities = [FavoritesMovieVo::class, FavoritesVo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesMovieDao(): FavoritesMovieDao
    abstract fun favoritesDao(): FavoritesDao
}
