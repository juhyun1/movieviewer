package com.themovieviewer.presentation.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.domain.usecase.GetMoviePopularPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor() : ViewModel() {
    val movieList: Flow<PagingData<Movie>> by lazy {
        val limited = 10
        getMoviePopularPagerUseCase.execute(viewModelScope, pageSize = limited)
    }

    @Inject
    lateinit var getMoviePopularPagerUseCase: GetMoviePopularPagerUseCase

}
