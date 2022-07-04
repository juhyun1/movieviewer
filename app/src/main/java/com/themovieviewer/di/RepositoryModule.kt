package com.themovieviewer.di

import com.themovieviewer.core.data.repository.FavoritesMovieRepository
import com.themovieviewer.core.data.repository.FavoritesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFavoritesRepository(
        favoritesDao: FavoritesDao
    ): FavoritesRepository {
        return FavoritesRepository(
            favoritesDao = favoritesDao
        )
    }

    @Singleton
    @Provides
    fun provideFavoritesMovieRepository(
        favoritesMovieDao: FavoritesMovieDao
    ): FavoritesMovieRepository {
        return FavoritesMovieRepository(
            favoritesMovieDao = favoritesMovieDao
        )
    }
}
