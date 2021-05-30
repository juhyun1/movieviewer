package com.themovieviewer.presentation.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.presentation.paging.NowPlayingDataSource
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import com.themovieviewer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): ViewModel() {
    @Inject lateinit var favoritesRepository: FavoritesRepository
    @Inject lateinit var favoritesMovieRepository: FavoritesMovieRepository
    var favoriteAddMode = false

    val nowPlayingList = Pager(PagingConfig(pageSize = 100)) {
        NowPlayingDataSource(movieRepository, movieDtoMapper)
    }.flow.cachedIn(viewModelScope)

    fun insertFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        viewModelScope.launch {
            favoritesRepository.insertFavorites(favorites)
            favoritesMovieRepository.insertFavoritesMovie(favoritesMovie)
        }
    }
}