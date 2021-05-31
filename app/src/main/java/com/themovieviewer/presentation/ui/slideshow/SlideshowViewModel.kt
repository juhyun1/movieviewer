package com.themovieviewer.presentation.ui.slideshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.repository.FavoritesMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val favoritesMovieRepository: FavoritesMovieRepository,
    private val daoMapper: DaoMapper
): ViewModel() {
    var favoriteRemoveMode = false
    val favoritesList = Pager(PagingConfig(pageSize = 100)) {
        favoritesMovieRepository.getFavoritesMovies()
    }.flow
        .map { pagingData ->
            pagingData
                .map { movie -> daoMapper.mapToDomainModel(movie) }
        }
        .cachedIn(viewModelScope)

}