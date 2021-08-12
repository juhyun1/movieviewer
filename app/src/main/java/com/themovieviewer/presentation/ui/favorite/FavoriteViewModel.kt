package com.themovieviewer.presentation.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.domain.usecase.GetFavoritePagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() : ViewModel() {
    private val pageSize = 100
    var favoriteRemoveMode = false
    @Inject
    lateinit var getFavoritePagerUseCase: GetFavoritePagerUseCase

    val favoritesList: Flow<PagingData<Movie>> by lazy {
        getFavoritePagerUseCase.execute(scope = viewModelScope, pageSize = pageSize)
    }
}
