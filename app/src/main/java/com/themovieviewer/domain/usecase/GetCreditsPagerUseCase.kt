package com.themovieviewer.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.themovieviewer.network.model.CreditsCastCrewDto
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.presentation.paging.CreditsDataSource
import com.themovieviewer.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetCreditsPagerUseCase(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): UseCase {

    fun execute(scope: CoroutineScope, personId: Int, language: String, pageSize: Int): Flow<PagingData<CreditsCastCrewDto>> {
        return Pager(PagingConfig(pageSize = pageSize)) {
            CreditsDataSource(
                movieRepository = movieRepository,
                movieDtoMapper = movieDtoMapper,
                movieId = personId,
                language = language
                )
        }.flow.cachedIn(scope)
    }
}
