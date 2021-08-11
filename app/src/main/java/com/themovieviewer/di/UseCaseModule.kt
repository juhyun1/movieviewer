package com.themovieviewer.di

import com.themovieviewer.domain.usecase.GetActingDataPagerUseCase
import com.themovieviewer.domain.usecase.GetMoviePopularPagerUseCase
import com.themovieviewer.domain.usecase.GetPeopleDetailsUseCase
import com.themovieviewer.domain.usecase.GetPeopleMovieCreditsPagerUseCase
import com.themovieviewer.network.model.CastCrewDtoMapper
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetMoviePopularPagerUseCase(
        movieRepository: MovieRepository,
        movieDtoMapper: MovieDtoMapper
    ): GetMoviePopularPagerUseCase {
        return GetMoviePopularPagerUseCase(
            movieRepository = movieRepository,
            movieDtoMapper = movieDtoMapper
        )
    }

    @Provides
    fun provideGetPeopleMovieCreditsPagerUseCase(
        movieRepository: MovieRepository,
        movieDtoMapper: MovieDtoMapper
    ): GetPeopleMovieCreditsPagerUseCase {
        return GetPeopleMovieCreditsPagerUseCase(
            movieRepository = movieRepository,
            movieDtoMapper = movieDtoMapper
        )
    }

    @Provides
    fun provideGetActingDataUseCase(
        movieRepository: MovieRepository,
        castCrewDtoMapper: CastCrewDtoMapper
    ): GetActingDataPagerUseCase {
        return GetActingDataPagerUseCase(
            movieRepository = movieRepository,
            castCrewDtoMapper = castCrewDtoMapper
        )
    }

    @Provides
    fun provideGetPeopleDetailsUseCase(
        movieRepository: MovieRepository
    ): GetPeopleDetailsUseCase {
        return GetPeopleDetailsUseCase(
            movieRepository = movieRepository
        )
    }
}