package com.themovieviewer.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieviewer.core.data.network.datasource.CreditsDataSource
import com.themovieviewer.core.data.network.datasource.RecommendationsDataSource
import com.themovieviewer.core.data.network.datasource.VideoDataSource
import com.themovieviewer.core.datastore.Language
import com.themovieviewer.core.data.repository.PreferencesRepository
import com.themovieviewer.core.model.data.MovieDetail
import com.themovieviewer.core.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val creditsDataSource: CreditsDataSource,
    private val trailerDataSource: VideoDataSource,
    private val recommendationsDataSource: RecommendationsDataSource,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    var movieID = 0
    private var locale: Language = runBlocking {
        preferencesRepository.getLanguage()
    }

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> get() = _movieDetail
    fun getCreditsDataSource(): CreditsDataSource = creditsDataSource.apply {
        this.movieId = movieID
        this.language = locale.getLocale()
    }
    fun getTrailerDataSource(): VideoDataSource = trailerDataSource.apply {
        this.movieId = movieID
        this.language = locale.getLocale()
    }
    fun getRecommendationsDataSource(): RecommendationsDataSource = recommendationsDataSource.apply {
        this.movieId = movieID
        this.language = locale.getLocale()
    }

    fun getDetailsInfo(movieId: Int) {
        this.movieID = movieId
        viewModelScope.launch {
            val detail = repository.getMovieDetails(language = locale.getLocale(), movie_id = movieId)
            _movieDetail.value = detail
        }
    }
}