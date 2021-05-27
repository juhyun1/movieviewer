package com.themovieviewer.presentation.ui.moviedetails

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.network.model.CreditsCastCrewDto
import com.themovieviewer.network.model.MovieDetailsResponse
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.MovieCreditsResponse
import com.themovieviewer.network.response.PeopleDetailsResponse
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.CreditsDataSource
import com.themovieviewer.presentation.paging.NowPlayingDataSource
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class MovieDetailsFragmentViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper,
    private val application: BaseApplication
): ViewModel() {

    val overview: MutableLiveData<String> = MutableLiveData("")
    val tagline: MutableLiveData<String> = MutableLiveData("")
    val genres: MutableLiveData<String> = MutableLiveData("")
    val voteAverage: MutableLiveData<String> = MutableLiveData("")
    val runtime: MutableLiveData<String> = MutableLiveData("")
    val releaseDate: MutableLiveData<String> = MutableLiveData("")
    val title: MutableLiveData<String> = MutableLiveData("")
    val backdropImage: MutableLiveData<Drawable> = MutableLiveData()
    val posterImage: MutableLiveData<Drawable> = MutableLiveData()
    val creditsList = Pager(PagingConfig(pageSize = 100)) {
        CreditsDataSource(movieRepository, movieDtoMapper, application.selectedMovie.id)
    }.flow.cachedIn(viewModelScope)

    init {
        init(application.selectedMovie)
    }

    fun init(movie: Movie) {
        val language = "en-US"
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            val movieDetailsResponse: MovieDetailsResponse = movieRepository.getMovieDetails(
                language = language,
                movie_id = movie.id
            )

            movieDetailsResponse.let{
                title.value = it.original_title
                releaseDate.value = it.release_date
                val temp = movieDetailsResponse.runtime?.div(60)
                val temp2 = movieDetailsResponse.runtime?.rem(60)
                val b = StringBuilder()
                b.append(temp)
                b.append("h ")
                b.append(temp2)
                b.append("m")
                runtime.value = b.toString()

                val list = ArrayList<String>()
                for (genres in it.genres) {
                    list.add(genres.name)
                }

                genres.value = TextUtils.join(",", list)
                val temp3 = (it.vote_average * 10).toInt().toString()
                voteAverage.value = "User Score $temp3%"
                tagline.value = it.tagline
                overview.value = it.overview
            }


            val base: String = "https://image.tmdb.org/t/p/w500"
            val imageURL: String = base
            var backdropBitmap: Drawable? = null
            var posterBitmap: Drawable? = null

            withContext(Dispatchers.IO){
                try {
                    // Download Image from URL
                    val input: InputStream = URL(base + movieDetailsResponse.backdrop_path).openStream()
                    backdropBitmap = BitmapDrawable(BitmapFactory.decodeStream(input))

                    val input2: InputStream = URL(base + movieDetailsResponse.poster_path).openStream()
                    posterBitmap = BitmapDrawable(BitmapFactory.decodeStream(input2))

                    withContext(Dispatchers.Main){
                        backdropImage.value = backdropBitmap
                        posterImage.value = posterBitmap
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            Log.d(TAG, "budget : " + movieDetailsResponse.budget + " revenue : " + movieDetailsResponse.revenue)
        }
    }
}