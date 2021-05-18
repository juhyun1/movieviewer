package com.themovieviewer.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieviewer.momain.model.Movie
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository

): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getTopRated(){
        val language = "en-US"
        val page = 1
        viewModelScope.launch {
            try {
                val movieList: List<Movie> = movieRepository.getTopRated(language = language, page = page)

                for (movie in movieList) {
                    Log.d(TAG, movie.toString())
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }
}