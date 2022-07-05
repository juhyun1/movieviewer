package com.themovieviewer.core.database.di

import android.content.Context
import androidx.room.Room
import com.themovieviewer.core.database.AppDatabase
import com.themovieviewer.core.database.DatabaseDataSource
import com.themovieviewer.core.database.dao.FavoritesDao
import com.themovieviewer.core.database.dao.FavoritesMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATABASE_NAME = "movie_viewer.db"


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFavoritesDao(appDatabase: AppDatabase): FavoritesDao {
        return appDatabase.favoritesDao()
    }

    @Provides
    fun provideFavoritesMovieDao(appDatabase: AppDatabase): FavoritesMovieDao {
        return appDatabase.favoritesMovieDao()
    }

    @Provides
    fun provideDatabaseDataSource(
        favoritesDao: FavoritesDao,
        favoritesMovieDao: FavoritesMovieDao,
    ): DatabaseDataSource {
        return DatabaseDataSource(
            favoritesMovieDao = favoritesMovieDao
        )
    }
}