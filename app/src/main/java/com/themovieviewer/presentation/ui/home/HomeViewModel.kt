package com.themovieviewer.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.presentation.paging.TopRatedListDataSource
import com.themovieviewer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
) : ViewModel() {
    var favoriteAddMode = false

    val topRatedList = Pager(PagingConfig(pageSize = 10)) {
        TopRatedListDataSource(movieRepository, movieDtoMapper)
    }.flow.cachedIn(viewModelScope)
}
