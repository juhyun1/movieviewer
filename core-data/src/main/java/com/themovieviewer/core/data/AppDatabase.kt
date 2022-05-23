package com.themovieviewer.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.themovieviewer.core.data.util.Converters
import com.themovieviewer.core.data.vo.Favorites
import com.themovieviewer.core.data.vo.FavoritesMovie

@TypeConverters(Converters::class)
@Database(entities = [FavoritesMovie::class, Favorites::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesMovieDao(): FavoritesMovieDao
    abstract fun favoritesDao(): FavoritesDao
}
