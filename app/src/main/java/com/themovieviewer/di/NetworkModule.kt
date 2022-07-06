package com.themovieviewer.di

import com.google.gson.GsonBuilder
import com.themovieviewer.core.data.network.MovieService
import com.themovieviewer.core.data.network.model.CastCrewDtoMapper
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.data.network.model.VideosDtoMapper
import com.themovieviewer.core.data.network.response.MovieDetailMapper
import com.themovieviewer.core.data.network.response.PeopleMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMovieDtoMapper(): MovieDtoMapper {
        return MovieDtoMapper()
    }

    @Singleton
    @Provides
    fun provideVideosDtoMapper(): VideosDtoMapper {
        return VideosDtoMapper()
    }

    @Singleton
    @Provides
    fun provideCastCrewDtoMapper(): CastCrewDtoMapper {
        return CastCrewDtoMapper()
    }

    @Singleton
    @Provides
    fun providePeopleMapper(): PeopleMapper {
        return PeopleMapper()
    }

    @Singleton
    @Provides
    fun provideMovieDetailMapper(): MovieDetailMapper {
        return MovieDetailMapper()
    }

    @Singleton
    @Provides
    fun provideMovieService(): MovieService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieService::class.java)
    }
}
