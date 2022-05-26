package com.themovieviewer.presentation.ui.toprated

import androidx.lifecycle.ViewModel
import com.themovieviewer.BaseApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val application: BaseApplication,
) : ViewModel() {
//    var favoriteAddMode = false
//    private val pageSize = 10
//    private val language = application.language
//    @Inject
//    lateinit var getTopRatedPagerUseCase: GetTopRatedPagerUseCase
//
//    val topRatedList: Flow<PagingData<Movie>> by lazy {
//        getTopRatedPagerUseCase.execute(viewModelScope, language = language, pageSize = pageSize)
//    }
}
