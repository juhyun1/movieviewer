package com.themovieviewer.di

import com.themovieviewer.core.data.repository.FavoritesMovieRepositoryImpl
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
    ): FavoritesRepository {
        return FavoritesRepository(
        )
    }

    @Singleton
    @Provides
    fun provideFavoritesMovieRepository(
    ): FavoritesMovieRepositoryImpl {
        return FavoritesMovieRepositoryImpl(
        )
    }
}
