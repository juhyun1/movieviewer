package com.themovieviewer.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.themovieviewer.core.database.dao.FavoritesDao
import com.themovieviewer.core.database.dao.FavoritesMovieDao
import com.themovieviewer.core.database.model.FavoritesMovieVo
import com.themovieviewer.core.database.model.FavoritesVo
import com.themovieviewer.core.database.util.Converters

@TypeConverters(Converters::class)
@Database(entities = [FavoritesMovieVo::class, FavoritesVo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesMovieDao(): FavoritesMovieDao
    abstract fun favoritesDao(): FavoritesDao
}
