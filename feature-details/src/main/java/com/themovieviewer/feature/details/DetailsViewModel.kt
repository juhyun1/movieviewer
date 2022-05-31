package com.themovieviewer.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieviewer.core.data.network.datasource.CreditsDataSource
import com.themovieviewer.core.data.network.datasource.NowPlayingDataSource
import com.themovieviewer.core.model.data.MovieDetail
import com.themovieviewer.core.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val creditsDataSource: CreditsDataSource
) : ViewModel() {

    private var movieID = 0
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> get() = _movieDetail
    fun getCreditsDataSource(): CreditsDataSource = creditsDataSource.apply { this.movieId = movieID }

    fun getDetailsInfo(movieId: Int) {
        this.movieID = movieId
        viewModelScope.launch {
            val detail = repository.getMovieDetails(movie_id = movieId)
            _movieDetail.value = detail
            Timber.d("Test : details : $detail")
        }
    }
}