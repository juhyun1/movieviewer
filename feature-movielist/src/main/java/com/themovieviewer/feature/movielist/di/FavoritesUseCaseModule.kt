package com.themovieviewer.feature.movielist.di

import com.themovieviewer.core.data.repository.FavoritesMovieRepository
import com.themovieviewer.feature.movielist.usecase.GetFavoriteUC
import com.themovieviewer.feature.movielist.usecase.InsertFavoriteUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesUseCaseModule {

    @Provides
    @Singleton
    fun provideInsertFavoriteUC(
        repository: FavoritesMovieRepository
    ): InsertFavoriteUC {
        return InsertFavoriteUC(repository = repository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteUC(
        repository: FavoritesMovieRepository
    ): GetFavoriteUC {
        return GetFavoriteUC(repository = repository)
    }
}