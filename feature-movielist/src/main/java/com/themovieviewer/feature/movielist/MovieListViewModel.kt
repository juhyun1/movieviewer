package com.themovieviewer.feature.movielist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.core.data.network.datasource.PopularDataSource
import com.themovieviewer.core.data.network.datasource.UpcomingDataSource
import com.themovieviewer.core.datastore.Category
import com.themovieviewer.core.datastore.repository.PreferencesRepository
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.feature.movielist.model.PreferenceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val nowPlayingDataSource: NowPlayingDataSource,
    private val popularDataSource: PopularDataSource,
    private val upcomingDataSource: UpcomingDataSource,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _preferenceState = mutableStateOf<PreferenceState>(PreferenceState.Hide)
    val preferenceState get() = _preferenceState

    private val _category = mutableStateOf<PagingSource<Int, Movie>>(getDataSource())
    val category get() = _category

    private val _pager = mutableStateOf(Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            maxSize = 1000
        )
    ) { getDataSource() })

    val pager get() = _pager

    private fun getDataSource(): PagingSource<Int, Movie> = runBlocking {

        when (preferencesRepository.getCategory()) {
            Category.NowPlaying -> {
                Timber.d("Test : getDataSource NowPlaying")
                nowPlayingDataSource
            }
            Category.Upcoming -> {
                Timber.d("Test : getDataSource Popular")
                upcomingDataSource
            }
            else -> {
                nowPlayingDataSource
            }
        }
    }

    fun onPreferencesStateChange(state: PreferenceState) {
        when(state) {
            PreferenceState.Show -> {
                _preferenceState.value = PreferenceState.Show
            }
            PreferenceState.Hide -> {
                _preferenceState.value = PreferenceState.Hide
            }
        }
    }

    fun onCategoryChanged(category: Category) {
        viewModelScope.launch {
            preferencesRepository.setCategory(category = category)
//            _category.value = getDataSource()
            _pager.value = Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true,
                    maxSize = 1000
                )
            ) { getDataSource() }
        }
    }
}