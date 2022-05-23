package com.themovieviewer.presentation.ui.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.core.data.vo.Favorites
import com.themovieviewer.core.data.vo.FavoritesMovie
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.usecase.GetNowPlayingPagerUseCase
import com.themovieviewer.core.model.usecase.InsertFavoriteMovieUseCase
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

    val nowPlayingList: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = pageSize)) {
        getNowPlayingPagerUseCase(language = language) as NowPlayingDataSource
    }.flow.cachedIn(viewModelScope)

    fun insertFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        viewModelScope.launch {
            insertFavoriteMovieUseCase.execute(favorites, favoritesMovie)
        }
    }
}
