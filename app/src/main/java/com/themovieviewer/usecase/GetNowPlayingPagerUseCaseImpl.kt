package com.themovieviewer.usecase

import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.model.repository.MovieRepository
import com.themovieviewer.core.model.usecase.GetNowPlayingPagerUseCase

class GetNowPlayingPagerUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): GetNowPlayingPagerUseCase {

    override operator fun invoke(language: String): NowPlayingDataSource {
        return NowPlayingDataSource(movieRepository = movieRepository, movieDtoMapper = movieDtoMapper, language = language)
    }
}
