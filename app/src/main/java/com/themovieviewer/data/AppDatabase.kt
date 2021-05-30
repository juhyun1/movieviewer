package com.themovieviewer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.util.Converters

@TypeConverters(Converters::class)
@Database(entities = [FavoritesMovie::class, Favorites::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun favoritesMovieDao(): FavoritesMovieDao
    abstract fun favoritesDao(): FavoritesDao
}