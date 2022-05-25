package com.themovieviewer.di

import com.themovieviewer.core.data.DaoMapper
import com.themovieviewer.core.data.network.model.CastCrewDtoMapper
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.data.network.model.VideosDtoMapper
import com.themovieviewer.core.data.network.response.MovieDetailMapper
import com.themovieviewer.core.data.network.response.PeopleMapper
import com.themovieviewer.core.data.repository.FavoritesMovieRepository
import com.themovieviewer.core.data.repository.FavoritesRepository
import com.themovieviewer.core.model.repository.MovieRepository
import com.themovieviewer.core.model.usecase.*
import com.themovieviewer.usecase.DeleteFavoriteMovieUseCaseImpl
import com.themovieviewer.usecase.GetCreditsPagerUseCaseImpl
import com.themovieviewer.usecase.GetNowPlayingPagerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

//    @Provides
//    fun provideGetMoviePopularPagerUseCase(
//        movieRepository: MovieRepository,
//        movieDtoMapper: MovieDtoMapper
//    ): GetMoviePopularPagerUseCase {
//        return GetMoviePopularPagerUseCase(
//            movieRepository = movieRepository,
//            movieDtoMapper = movieDtoMapper
//        )
//    }
//
//    @Provides
//    fun provideGetPeopleMovieCreditsPagerUseCase(
//        movieRepository: MovieRepository,
//        movieDtoMapper: MovieDtoMapper
//    ): GetPeopleMovieCreditsPagerUseCase {
//        return GetPeopleMovieCreditsPagerUseCase(
//            movieRepository = movieRepository,
//            movieDtoMapper = movieDtoMapper
//        )
//    }
//
//    @Provides
//    fun provideGetActingDataUseCase(
//        movieRepository: MovieRepository,
//        castCrewDtoMapper: CastCrewDtoMapper
//    ): GetActingDataPagerUseCase {
//        return GetActingDataPagerUseCase(
//            movieRepository = movieRepository,
//            castCrewDtoMapper = castCrewDtoMapper
//        )
//    }
//
//    @Provides
//    fun provideGetPeopleDetailsUseCase(
//        movieRepository: MovieRepository,
//        peopleMapper: PeopleMapper
//    ): GetPeopleDetailsUseCase {
//        return GetPeopleDetailsUseCase(
//            movieRepository = movieRepository,
//            peopleMapper = peopleMapper
//        )
//    }
//
//    @Provides
//    fun provideGetRecommendationsPagerUseCase(
//        movieRepository: MovieRepository,
//        movieDtoMapper: MovieDtoMapper
//    ): GetRecommendationsPagerUseCase {
//        return GetRecommendationsPagerUseCase(
//            movieRepository = movieRepository,
//            movieDtoMapper = movieDtoMapper
//        )
//    }
//
//    @Provides
//    fun provideGetVideoPagerUseCase(
//        movieRepository: MovieRepository,
//        videosDtoMapper: VideosDtoMapper
//    ): GetVideoPagerUseCase {
//        return GetVideoPagerUseCase(
//            movieRepository = movieRepository,
//            videosDtoMapper = videosDtoMapper
//        )
//    }
//
//    @Provides
//    fun provideInsertFavoriteMovieUseCase(
//        favoritesRepository: FavoritesRepository,
//        favoritesMovieRepository: FavoritesMovieRepository
//    ): InsertFavoriteMovieUseCase {
//        return InsertFavoriteMovieUseCase(
//            favoritesRepository = favoritesRepository,
//            favoritesMovieRepository = favoritesMovieRepository
//        )
//    }
//
//    @Provides
//    fun provideGetMovieDetailsUseCase(
//        movieRepository: MovieRepository,
//        movieDetailMapper: MovieDetailMapper
//    ): GetMovieDetailsUseCase {
//        return GetMovieDetailsUseCase(
//            movieRepository = movieRepository,
//            movieDetailMapper = movieDetailMapper
//        )
//    }
//
//    @Provides
//    fun provideGetTopRatedPagerUseCase(
//        movieRepository: MovieRepository,
//        movieDtoMapper: MovieDtoMapper
//    ): GetTopRatedPagerUseCase {
//        return GetTopRatedPagerUseCase(
//            movieRepository = movieRepository,
//            movieDtoMapper = movieDtoMapper
//        )
//    }
//
//    @Provides
//    fun provideGetFavoritePagerUseCase(
//        favoritesMovieRepository: FavoritesMovieRepository,
//        daoMapper: DaoMapper
//    ): GetFavoritePagerUseCase {
//        return GetFavoritePagerUseCase(
//            favoritesMovieRepository = favoritesMovieRepository,
//            daoMapper = daoMapper
//        )
//    }
}

@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule2 {
    @Binds
    fun bindGetCreditsPagerUseCase(impl: GetCreditsPagerUseCaseImpl): GetCreditsPagerUseCase

    @Binds
    fun bindGetNowPlayingPagerUseCase(impl: GetNowPlayingPagerUseCaseImpl): GetNowPlayingPagerUseCase

    @Binds
    fun bindDeleteFavoriteMovieUseCase(impl: DeleteFavoriteMovieUseCaseImpl): DeleteFavoriteMovieUseCase

}