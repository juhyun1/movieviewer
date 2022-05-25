package com.themovieviewer.usecase

import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.core.model.repository.MovieRepository
import com.themovieviewer.core.model.usecase.GetNowPlayingPagerUseCase
import javax.inject.Inject

class GetNowPlayingPagerUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
): GetNowPlayingPagerUseCase {

    override operator fun invoke(language: String): NowPlayingDataSource {
        return NowPlayingDataSource(movieRepository = movieRepository, language = language)
    }
}
