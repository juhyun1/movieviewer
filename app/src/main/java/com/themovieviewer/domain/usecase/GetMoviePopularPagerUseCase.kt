package com.themovieviewer.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.presentation.paging.PopularDataSource
import com.themovieviewer.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetMoviePopularPagerUseCase(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): UseCase {

    fun execute(scope: CoroutineScope, pageSize: Int): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(pageSize = pageSize)) {
            PopularDataSource(movieRepository = movieRepository, movieDtoMapper = movieDtoMapper)
        }.flow.cachedIn(scope)
    }
}