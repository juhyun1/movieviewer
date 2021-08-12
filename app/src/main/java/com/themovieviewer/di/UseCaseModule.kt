package com.themovieviewer.di

import com.themovieviewer.domain.usecase.*
import com.themovieviewer.network.model.CastCrewDtoMapper
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.model.VideosDtoMapper
import com.themovieviewer.network.response.MovieDetailMapper
import com.themovieviewer.network.response.PeopleMapper
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
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
        movieRepository: MovieRepository,
        peopleMapper: PeopleMapper
    ): GetPeopleDetailsUseCase {
        return GetPeopleDetailsUseCase(
            movieRepository = movieRepository,
            peopleMapper = peopleMapper
        )
    }

    @Provides
    fun provideGetCreditsPagerUseCase(
        movieRepository: MovieRepository,
        movieDtoMapper: MovieDtoMapper
    ): GetCreditsPagerUseCase {
        return GetCreditsPagerUseCase(
            movieRepository = movieRepository,
            movieDtoMapper = movieDtoMapper
        )
    }

    @Provides
    fun provideGetRecommendationsPagerUseCase(
        movieRepository: MovieRepository,
        movieDtoMapper: MovieDtoMapper
    ): GetRecommendationsPagerUseCase {
        return GetRecommendationsPagerUseCase(
            movieRepository = movieRepository,
            movieDtoMapper = movieDtoMapper
        )
    }

    @Provides
    fun provideGetVideoPagerUseCase(
        movieRepository: MovieRepository,
        videosDtoMapper: VideosDtoMapper
    ): GetVideoPagerUseCase {
        return GetVideoPagerUseCase(
            movieRepository = movieRepository,
            videosDtoMapper = videosDtoMapper
        )
    }

    @Provides
    fun provideInsertFavoriteMovieUseCase(
        favoritesRepository: FavoritesRepository,
        favoritesMovieRepository: FavoritesMovieRepository
    ): InsertFavoriteMovieUseCase {
        return InsertFavoriteMovieUseCase(
            favoritesRepository = favoritesRepository,
            favoritesMovieRepository = favoritesMovieRepository
        )
    }

    @Provides
    fun provideDeleteFavoriteMovieUseCase(
        favoritesRepository: FavoritesRepository,
        favoritesMovieRepository: FavoritesMovieRepository
    ): DeleteFavoriteMovieUseCase {
        return DeleteFavoriteMovieUseCase(
            favoritesRepository = favoritesRepository,
            favoritesMovieRepository = favoritesMovieRepository
        )
    }

    @Provides
    fun provideGetMovieDetailsUseCase(
        movieRepository: MovieRepository,
        movieDetailMapper: MovieDetailMapper
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(
            movieRepository = movieRepository,
            movieDetailMapper = movieDetailMapper
        )
    }
}
