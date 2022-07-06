package com.themovieviewer.presentation.ui.popular

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor() : ViewModel() {
//    private val limited = 10
//    val movieList: Flow<PagingData<Movie>> by lazy {
//        getMoviePopularPagerUseCase.execute(viewModelScope, pageSize = limited)
//    }
//
//    @Inject
//    lateinit var getMoviePopularPagerUseCase: GetMoviePopularPagerUseCase
}
