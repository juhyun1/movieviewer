package com.themovieviewer.core.model.usecase
//
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import androidx.paging.cachedIn
//import com.themovieviewer.core.model.data.CastCrew
//import com.themovieviewer.core.model.usecase.UseCase
//import com.themovieviewer.network.model.CastCrewDtoMapper
//import com.themovieviewer.presentation.paging.ActingDataSource
//import com.themovieviewer.repository.MovieRepository
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.flow.Flow
//
//class GetActingDataPagerUseCase(
////    private val movieRepository: MovieRepository,
////    private val castCrewDtoMapper: CastCrewDtoMapper
////): UseCase {
////
////    fun execute(scope: CoroutineScope, personId: Int, language: String, pageSize: Int): Flow<PagingData<CastCrew>> {
////        return Pager(PagingConfig(pageSize = pageSize)) {
////            ActingDataSource(movieRepository = movieRepository, castCrewDtoMapper = castCrewDtoMapper, personId = personId, language = language)
////        }.flow.cachedIn(scope)
////    }
//}
