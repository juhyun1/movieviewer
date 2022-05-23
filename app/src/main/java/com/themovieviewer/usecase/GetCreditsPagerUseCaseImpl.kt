package com.themovieviewer.usecase

import com.themovieviewer.core.data.network.datasource.CreditsDataSource
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.model.repository.MovieRepository
import com.themovieviewer.core.model.usecase.GetCreditsPagerUseCase
import com.themovieviewer.core.model.usecase.UseCase
import kotlinx.coroutines.CoroutineScope

class GetCreditsPagerUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): GetCreditsPagerUseCase {
    override operator fun invoke(movieId: Int, language: String): CreditsDataSource = CreditsDataSource(
                movieRepository = movieRepository,
                movieDtoMapper = movieDtoMapper,
                movieId = movieId,
                language = language
            )
}
