package com.themovieviewer.presentation.ui.nowplaying

import androidx.lifecycle.ViewModel
import com.themovieviewer.BaseApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    application: BaseApplication,
) : ViewModel() {
    private val language = application.language
    private val pageSize = 100
    var favoriteAddMode = false

//    @Inject
//    lateinit var insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase
//
//    val nowPlayingList: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = pageSize)) {
//        getNowPlayingPagerUseCase(language = language) as NowPlayingDataSource
//    }.flow.cachedIn(viewModelScope)
//
//    fun insertFavoriteMovie(favoritesDto: FavoritesVo, favoritesMovie: FavoritesMovieVo) {
//        viewModelScope.launch {
// //            insertFavoriteMovieUseCase.execute(favoritesDto, favoritesMovie)
//        }
//    }
}
