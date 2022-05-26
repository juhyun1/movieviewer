package com.themovieviewer.core.model.usecase

//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import androidx.paging.cachedIn
//import com.themovieviewer.core.model.data.Movie
//import com.themovieviewer.core.model.usecase.UseCase
//import com.themovieviewer.network.model.MovieDtoMapper
//import com.themovieviewer.presentation.paging.TopRatedListDataSource
//import com.themovieviewer.repository.MovieRepository
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.flow.Flow
//
//class GetTopRatedPagerUseCase(
//    private val movieRepository: MovieRepository,
//    private val movieDtoMapper: MovieDtoMapper
//): UseCase {
//    fun execute(scope: CoroutineScope, language: String, pageSize: Int): Flow<PagingData<Movie>> {
//        return Pager(PagingConfig(pageSize = pageSize)) {
//            TopRatedListDataSource(movieRepository = movieRepository, movieDtoMapper = movieDtoMapper, language = language)
//        }.flow.cachedIn(scope)
//    }
//}
