package com.themovieviewer.di

import com.themovieviewer.data.FavoritesDao
import com.themovieviewer.data.FavoritesMovieDao
import com.themovieviewer.network.MovieService
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.core.data.repository.MovieRepositoryImpl
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
    fun provideMovieRepository(
        movieService: MovieService
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieService = movieService
        )
    }

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
