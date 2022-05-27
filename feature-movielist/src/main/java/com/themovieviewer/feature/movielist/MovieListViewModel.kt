package com.themovieviewer.feature.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.core.model.usecase.GetNowPlayingPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getNowPlayingPagerUseCase: GetNowPlayingPagerUseCase,
) : ViewModel() {

    fun load() {
        viewModelScope.launch(IO) {
            try {
                when(val dataSource = getNowPlayingPagerUseCase(language = "en-US")) {
                    is NowPlayingDataSource -> {
                        dataSource.test()
                    }
                }
            } catch(e: Exception) {
                Timber.e(e)
            }

        }
    }

}