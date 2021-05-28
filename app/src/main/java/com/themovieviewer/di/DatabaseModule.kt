package com.themovieviewer.di

import android.content.Context
import com.themovieviewer.data.AppDatabase
import com.themovieviewer.data.FavoritesDao
import com.themovieviewer.data.FavoritesMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): FavoritesMovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    fun provideFavoritesDao(appDatabase: AppDatabase): FavoritesDao {
        return appDatabase.favoritesDao()
    }
}