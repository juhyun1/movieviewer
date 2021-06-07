package com.themovieviewer.presentation.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.presentation.paging.PopularDataSource
import com.themovieviewer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
) : ViewModel() {
    val movieList = Pager(PagingConfig(pageSize = 10)) {
        PopularDataSource(movieRepository, movieDtoMapper)
    }.flow.cachedIn(viewModelScope)
}
