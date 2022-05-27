package com.themovieviewer.core.data.di

import com.themovieviewer.core.data.repository.MovieRepositoryImpl
import com.themovieviewer.core.model.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule2 {
    @Binds
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}
