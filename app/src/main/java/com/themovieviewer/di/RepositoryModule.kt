package com.themovieviewer.di

import com.themovieviewer.network.MovieService
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.repository.MovieRepositoryImpl
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
    fun provideRecipeRepository(
        movieService: MovieService
    ): MovieRepository{
        return MovieRepositoryImpl(
            movieService = movieService
        )
    }
}