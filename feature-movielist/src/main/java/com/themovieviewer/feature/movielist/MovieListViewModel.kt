package com.themovieviewer.feature.movielist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.feature.movielist.model.PreferenceState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val nowPlayingDataSource: NowPlayingDataSource,
) : ViewModel() {

    private val _preferenceState = mutableStateOf<PreferenceState>(PreferenceState.Hide)
    val preferenceState get() = _preferenceState

    fun getNowPlayingDataSource(): NowPlayingDataSource = nowPlayingDataSource

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