package com.themovieviewer.presentation.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.themovieviewer.data.Favorites
import com.themovieviewer.data.FavoritesMovie
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.presentation.paging.NowPlayingDataSource
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import com.themovieviewer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): ViewModel() {
    @Inject lateinit var favoritesRepository: FavoritesRepository
    @Inject lateinit var favoritesMovieRepository: FavoritesMovieRepository

    val nowPlayingList = Pager(PagingConfig(pageSize = 100)) {
        NowPlayingDataSource(movieRepository, movieDtoMapper)
    }.flow.cachedIn(viewModelScope)


    fun test(fa: Favorites, fmovie: FavoritesMovie) {
        viewModelScope.launch {
            favoritesRepository.insertFavorites(fa)
            favoritesMovieRepository.insertFavoritesMovie(fmovie)
        }
    }
}