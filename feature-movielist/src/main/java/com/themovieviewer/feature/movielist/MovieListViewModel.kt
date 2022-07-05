package com.themovieviewer.feature.movielist

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.core.data.network.datasource.PopularDataSource
import com.themovieviewer.core.data.network.datasource.TopRatedListDataSource
import com.themovieviewer.core.data.network.datasource.UpcomingDataSource
import com.themovieviewer.core.datastore.Category
import com.themovieviewer.core.datastore.Language
import com.themovieviewer.core.data.repository.PreferencesRepository
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.feature.movielist.model.PreferenceState
import com.themovieviewer.feature.movielist.util.title
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val nowPlayingDataSource: NowPlayingDataSource,
    private val popularDataSource: PopularDataSource,
    private val upcomingDataSource: UpcomingDataSource,
    private val topRatedListDataSource: TopRatedListDataSource,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val _preferenceState = mutableStateOf<PreferenceState>(PreferenceState.Hide)
    val preferenceState get() = _preferenceState

    private val _pager = mutableStateOf(Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            maxSize = 1000
        )
    ) { getDataSource() })

    @StringRes private var _titleState = mutableStateOf<Int>( R.string.bottom_sheet_item_now_playing )
    val titleState get() = _titleState

    private var _categoryState = mutableStateOf( Category.NowPlaying )
    val categoryState get() = _categoryState

    private var _languageState = mutableStateOf( Language.English )
    val languageState get() = _languageState

    val pager get() = _pager

    init {
        viewModelScope.launch {
            _categoryState.value = preferencesRepository.getCategory()
            _languageState.value = preferencesRepository.getLanguage()
            _titleState.value = _categoryState.value.title()
        }
    }

    private fun getDataSource(): PagingSource<Int, Movie> = runBlocking {

        val language: Language = runBlocking {
            preferencesRepository.getLanguage()
        }

        when (preferencesRepository.getCategory()) {
            Category.NowPlaying -> {
                nowPlayingDataSource.apply { this.language = language.getLocale() }
            }
            Category.Upcoming -> {
                upcomingDataSource.apply { this.language = language.getLocale() }
            }
            Category.TopRate -> {
                topRatedListDataSource.apply { this.language = language.getLocale() }
            }
            Category.Popular -> {
                popularDataSource.apply { this.language = language.getLocale() }
            }
            else -> {
                nowPlayingDataSource.apply { this.language = language.getLocale() }
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
            _categoryState.value = category
            preferencesRepository.setCategory(category = category)
            _pager.value = Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true,
                    maxSize = 1000
                )
            ) { getDataSource() }
        }
        _titleState.value = category.title()
    }
    fun onLanguageChanged(language: Language) {
        viewModelScope.launch {
            _languageState.value = language
            preferencesRepository.setLanguage(language = language)
            _pager.value = Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true,
                    maxSize = 1000
                )
            ) { getDataSource() }
        }
    }

    fun onClickBookMark(movieId: Int) {

    }

    suspend fun checkBookMark(movieId: Int): Boolean {

        return false
    }
}