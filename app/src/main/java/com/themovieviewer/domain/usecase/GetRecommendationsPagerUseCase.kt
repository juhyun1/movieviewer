package com.themovieviewer.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.presentation.paging.RecommendationsDataSource
import com.themovieviewer.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetRecommendationsPagerUseCase(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): UseCase {

    fun execute(scope: CoroutineScope, personId: Int, language: String, pageSize: Int): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(pageSize = pageSize)) {
            RecommendationsDataSource(
                movieRepository = movieRepository,
                movieDtoMapper = movieDtoMapper,
                movieId = personId,
                language = language
            )
        }.flow.cachedIn(scope)
    }
}
