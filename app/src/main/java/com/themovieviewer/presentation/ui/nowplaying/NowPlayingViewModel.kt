package com.themovieviewer.presentation.ui.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.domain.usecase.GetNowPlayingPagerUseCase
import com.themovieviewer.domain.usecase.InsertFavoriteMovieUseCase
import com.themovieviewer.presentation.BaseApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    application: BaseApplication,
) : ViewModel() {
    private val language = application.language
    private val pageSize = 100
    var favoriteAddMode = false

    @Inject
    lateinit var getNowPlayingPagerUseCase: GetNowPlayingPagerUseCase
    @Inject
    lateinit var insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase

    val nowPlayingList: Flow<PagingData<Movie>> by lazy {
        getNowPlayingPagerUseCase.execute(scope = viewModelScope, language = language, pageSize = pageSize)
    }

    fun insertFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        viewModelScope.launch {
            insertFavoriteMovieUseCase.execute(favorites, favoritesMovie)
        }
    }
}
