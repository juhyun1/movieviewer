package com.themovieviewer.presentation.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.domain.model.MovieDetail
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.model.VideosDtoMapper
import com.themovieviewer.network.response.MovieDetailMapper
import com.themovieviewer.network.response.MovieDetailsResponse
import com.themovieviewer.network.response.VideosResponse
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.CreditsDataSource
import com.themovieviewer.presentation.paging.RecommendationsDataSource
import com.themovieviewer.presentation.paging.VideoDataSource
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import com.themovieviewer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsFragmentViewModel @Inject constructor(
    private val baseApplication: BaseApplication,
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper,
    private val videosDtoMapper: VideosDtoMapper,
    private val application: BaseApplication,
    private val favoritesRepository: FavoritesRepository,
    private val favoritesMovieRepository: FavoritesMovieRepository
) : ViewModel() {


    lateinit var movieDetail: MovieDetail

    val creditsList = Pager(PagingConfig(pageSize = 100)) {
        CreditsDataSource(movieRepository, movieDtoMapper, application.selectedMovie!!.id, baseApplication.language)
    }.flow.cachedIn(viewModelScope)

    val movieList = Pager(PagingConfig(pageSize = 100)) {
        RecommendationsDataSource(movieRepository, movieDtoMapper, application.selectedMovie!!.id, baseApplication.language)
    }.flow.cachedIn(viewModelScope)

    val videoList = Pager(PagingConfig(pageSize = 100)) {
        VideoDataSource(movieRepository, videosDtoMapper, application.selectedMovie!!.id, baseApplication.language)
    }.flow.cachedIn(viewModelScope)

    init {
        init(application.selectedMovie)
    }

    @Inject
    lateinit var movieDetailMapper: MovieDetailMapper

    val initDataDone = MutableLiveData(false)

    private fun init(movie: Movie?) {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            movie?.let {
                val movieDetailsResponse: MovieDetailsResponse = movieRepository.getMovieDetails(
                    language = baseApplication.language,
                    movie_id = movie.id
                )

                movieDetail = movieDetailMapper.mapToDomainModel(movieDetailsResponse)
//                movieDetailsResponse.let {
//                    title.value = it.title
//                    releaseDate.value = it.release_date
//                    val temp = movieDetailsResponse.runtime?.div(60)
//                    val temp2 = movieDetailsResponse.runtime?.rem(60)
//                    val b = StringBuilder()
//                    b.append(temp)
//                    b.append("h ")
//                    b.append(temp2)
//                    b.append("m")
//                    runtime.value = b.toString()
//
//                    val list = ArrayList<String>()
//                    for (genres in it.genres) {
//                        list.add(genres.name)
//                    }
//
//                    genres.value = TextUtils.join(",", list)
//                    val temp3 = (it.vote_average * 10).toInt().toString()
//                    voteAverage.value = "User Score $temp3%"
//                    tagline.value = it.tagline
//                    overview.value = it.overview
//                    status.value = it.status
//                    originalLanguage.value = it.original_language
//                    val format = DecimalFormat("###,###,###,###")
//                    budget.value = "$${format.format(it.budget)}"
//                    revenue.value = "$${format.format(it.revenue)}"
//
//                    val base = "https://image.tmdb.org/t/p/w500"
//                    backdropImage.value = base + movieDetailsResponse.backdrop_path
//                    posterImage.value = base + movieDetailsResponse.poster_path
//                }

                val videosResponse: VideosResponse = movieRepository.getVideos(
                    language = baseApplication.language,
                    movie_id = movie.id
                )

                if (videosResponse.results.isNotEmpty()) {
                    movieDetail.isTrailer = true
                }

                initDataDone.value = true
            }
        }
    }

    fun insertFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        viewModelScope.launch {
            favoritesRepository.insertFavorites(favorites)
            favoritesMovieRepository.insertFavoritesMovie(favoritesMovie)
        }
    }

    fun deleteFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        viewModelScope.launch {
            favoritesRepository.deleteFavorites(favorites)
            favoritesMovieRepository.deleteFavoritesMovie(favoritesMovie)
        }
    }

    fun showPoster(show: Boolean) {
        movieDetail.showPoster = show
        initDataDone.value = true
    }
}
