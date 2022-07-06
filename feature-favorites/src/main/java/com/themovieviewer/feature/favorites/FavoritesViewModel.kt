package com.themovieviewer.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.themovieviewer.core.data.mapper.asModel
import com.themovieviewer.core.data.repository.FavoritesMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesMovieRepository
) : ViewModel() {
    private val _pager = Pager(PagingConfig(
        pageSize = 20,
        enablePlaceholders = true,
        maxSize = 1000)) {
        repository.getFavoritesMovies()
    }.flow
    .map { pagingData ->
        pagingData.map { movie -> movie.asModel() }
    }
//    .cachedIn(viewModelSc)

    val pager get() = _pager

    private fun getDataSource() = repository.getFavoritesMovies()
}