package com.themovieviewer.core.model.usecase
//
//import androidx.paging.*
//import com.themovieviewer.data.DaoMapper
//import com.themovieviewer.core.model.data.Movie
//import com.themovieviewer.core.model.usecase.UseCase
//import com.themovieviewer.repository.FavoritesMovieRepository
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class GetFavoritePagerUseCase(
//    private val favoritesMovieRepository: FavoritesMovieRepository,
//    private val daoMapper: DaoMapper
//): UseCase {
//    fun execute(scope: CoroutineScope, pageSize: Int): Flow<PagingData<Movie>> {
//        return Pager(PagingConfig(pageSize = pageSize)) {
//            favoritesMovieRepository.getFavoritesMovies()
//        }.flow
//            .map { pagingData ->
//                pagingData.map { movie -> daoMapper.mapToDomainModel(movie) }
//            }
//            .cachedIn(scope)
//    }
//}
