package com.themovieviewer.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.NowPlayingDataSource
import com.themovieviewer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val baseApplication: BaseApplication,
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
) : ViewModel() {

    val nowPlayingList = Pager(PagingConfig(pageSize = 100)) {
        NowPlayingDataSource(movieRepository, movieDtoMapper, baseApplication.language)
    }.flow.cachedIn(viewModelScope)
}
