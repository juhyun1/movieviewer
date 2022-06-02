package com.themovieviewer.core.data.di

import com.themovieviewer.core.data.network.datasource.*
import com.themovieviewer.core.model.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun providesNowPlayingDataSource(
        movieRepository: MovieRepository
    ): NowPlayingDataSource {
        return NowPlayingDataSource(movieRepository = movieRepository)
    }

    @Provides
    fun providesCreditsDataSource(
        movieRepository: MovieRepository
    ): CreditsDataSource {
        return CreditsDataSource(movieRepository = movieRepository)
    }

    @Provides
    fun providesVideoDataSource(
        movieRepository: MovieRepository
    ): VideoDataSource {
        return VideoDataSource(movieRepository = movieRepository)
    }

    @Provides
    fun providesRecommendationsDataSource(
        movieRepository: MovieRepository
    ): RecommendationsDataSource {
        return RecommendationsDataSource(movieRepository = movieRepository)
    }

    @Provides
    fun providesPeopleMovieCreditsDataSource(
        movieRepository: MovieRepository
    ): PeopleMovieCreditsDataSource {
        return PeopleMovieCreditsDataSource(movieRepository = movieRepository)
    }

}
