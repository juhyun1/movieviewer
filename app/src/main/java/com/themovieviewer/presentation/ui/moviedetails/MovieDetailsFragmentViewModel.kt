package com.themovieviewer.presentation.ui.moviedetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.network.model.MovieDetailsResponse
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.MovieCreditsResponse
import com.themovieviewer.network.response.PeopleDetailsResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsFragmentViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper
): ViewModel() {

    fun test(movie: Movie) {
        val language = "en-US"
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            val movieDetailsResponse: MovieDetailsResponse = movieRepository.getMovieDetails(
                language = language,
                movie_id = movie.id
            )

            Log.d(TAG, "budget : " + movieDetailsResponse.budget + " revenue : " + movieDetailsResponse.revenue)

            val movieCreditsResponse: MovieCreditsResponse = movieRepository.getMovieCredits(
                language = language,
                movie_id = movie.id
            )
            Log.d(TAG, "Name : " + movieCreditsResponse.cast[0].name)

            val peopleDetailsResponse: PeopleDetailsResponse = movieRepository.getPeopleDetails(
                language = language,
                person_id = movieCreditsResponse.cast[0].id
            )
            Log.d(TAG, "Name : " + peopleDetailsResponse.name + " birthday " + peopleDetailsResponse.birthday)
        }
    }
}
