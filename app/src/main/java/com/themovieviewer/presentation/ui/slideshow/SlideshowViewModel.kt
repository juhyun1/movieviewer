package com.themovieviewer.presentation.ui.slideshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val favoritesMovieRepository: FavoritesMovieRepository,
    private val daoMapper: DaoMapper
): ViewModel() {

    val favoritesList = Pager(PagingConfig(pageSize = 100)) {
        favoritesMovieRepository.getFavoritesMovies()
//        val list: ArrayList<FavoritesMovie> = ArrayList()
//        for(favorite in favoritesList) {
//            list.add(favoritesMovieRepository.getFavoritesMovies(favorite.kindId))
//        }
//        FavoritesDataSource(favoritesRepository, favoritesMovieRepository, daoMapper)
    }.flow
        .map { pagingData ->
            pagingData
                .map { cheese -> daoMapper.mapToDomainModel(cheese) }
        }
        .cachedIn(viewModelScope)
}