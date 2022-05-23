package com.themovieviewer.presentation.ui.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.usecase.GetTopRatedPagerUseCase
import com.themovieviewer.presentation.BaseApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val application: BaseApplication,
) : ViewModel() {
    var favoriteAddMode = false
    private val pageSize = 10
    private val language = application.language
    @Inject
    lateinit var getTopRatedPagerUseCase: GetTopRatedPagerUseCase

    val topRatedList: Flow<PagingData<Movie>> by lazy {
        getTopRatedPagerUseCase.execute(viewModelScope, language = language, pageSize = pageSize)
    }
}
