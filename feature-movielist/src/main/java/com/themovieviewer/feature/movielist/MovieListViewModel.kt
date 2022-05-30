package com.themovieviewer.feature.movielist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.usecase.GetNowPlayingPagerUseCase
import com.themovieviewer.feature.movielist.model.PreferenceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getNowPlayingPagerUseCase: GetNowPlayingPagerUseCase,
) : ViewModel() {

    private val _preferenceState = mutableStateOf<PreferenceState>(PreferenceState.Hide)
    val preferenceState get() = _preferenceState

    val nowPlayingList: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        getNowPlayingPagerUseCase(language = "en-US") as NowPlayingDataSource
    }.flow.cachedIn(viewModelScope)

    fun getNowPlayingDataSource(): NowPlayingDataSource = getNowPlayingPagerUseCase(language = "en-US") as NowPlayingDataSource

    fun onPreferencesStateChange(state: PreferenceState) {
        Timber.d("Test : onPreferencesStateChange : $state")
        when(state) {
            PreferenceState.Show -> {
                _preferenceState.value = PreferenceState.Show
            }
            PreferenceState.Hide -> {
                _preferenceState.value = PreferenceState.Hide
            }
        }
    }
}