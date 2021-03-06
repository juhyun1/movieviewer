package com.themovieviewer.core.model.usecase

//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import androidx.paging.cachedIn
//import com.themovieviewer.core.model.data.Trailer
//import com.themovieviewer.core.data.repository.MovieRepository
//import com.themovieviewer.core.model.usecase.UseCase
//import com.themovieviewer.network.model.VideosDtoMapper
//import com.themovieviewer.presentation.paging.VideoDataSource
//import com.themovieviewer.repository.MovieRepository
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.flow.Flow
//
//class GetVideoPagerUseCase(
//    private val movieRepository: MovieRepository,
//    private val videosDtoMapper: VideosDtoMapper
//): UseCase {
//
//    operator fun invoke(scope: CoroutineScope, personId: Int, language: String, pageSize: Int): Flow<PagingData<Trailer>> {
//        return Pager(PagingConfig(pageSize = pageSize)) {
//            VideoDataSource(
//                movieRepository = movieRepository,
//                videosDtoMapper = videosDtoMapper,
//                movieId = personId,
//                language = language
//            )
//        }.flow.cachedIn(scope)
//    }
//}
