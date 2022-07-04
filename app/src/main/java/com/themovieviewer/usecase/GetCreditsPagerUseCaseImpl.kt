package com.themovieviewer.usecase

import com.themovieviewer.core.data.network.datasource.CreditsDataSource
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.data.repository.MovieRepository
import com.themovieviewer.core.model.usecase.GetCreditsPagerUseCase
import javax.inject.Inject

class GetCreditsPagerUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): GetCreditsPagerUseCase {
    override operator fun invoke(movieId: Int, language: String): CreditsDataSource = CreditsDataSource(
                movieRepository = movieRepository,
                movieId = movieId,
                language = language
            )
}
