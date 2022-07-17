package com.themovieviewer.core.data.di

import com.google.gson.GsonBuilder
import com.themovieviewer.core.data.network.MovieService
import com.themovieviewer.core.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule2 {
    @Binds
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindPreferencesRepository(impl: PreferencesRepositoryImpl): PreferencesRepository

    @Binds
    fun bindFavoritesMovieRepository(impl: FavoritesMovieRepositoryImpl): FavoritesMovieRepository
}