package com.themovieviewer.presentation.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.domain.model.MovieDetail
import com.themovieviewer.domain.model.Trailer
import com.themovieviewer.domain.usecase.*
import com.themovieviewer.network.model.CreditsCastCrewDto
import com.themovieviewer.presentation.BaseApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsFragmentViewModel @Inject constructor(
    private val application: BaseApplication,

) : ViewModel() {

    private val pageSize = 100
    lateinit var movieDetail: MovieDetail
    private val language = application.language

    @Inject
    lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    @Inject
    lateinit var getCreditsPagerUseCase: GetCreditsPagerUseCase
    @Inject
    lateinit var getRecommendationsPagerUseCase: GetRecommendationsPagerUseCase
    @Inject
    lateinit var getVideoPagerUseCase: GetVideoPagerUseCase
    @Inject
    lateinit var insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase
    @Inject
    lateinit var deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase

    val creditsList: Flow<PagingData<CreditsCastCrewDto>> by lazy {
        getCreditsPagerUseCase.execute(
            scope = viewModelScope, personId = application.selectedMovie!!.id,
            language = language, pageSize = pageSize)
    }
    val movieList: Flow<PagingData<Movie>> by lazy {
        getRecommendationsPagerUseCase.execute(
            scope = viewModelScope, personId = application.selectedMovie!!.id,
            language = language, pageSize = pageSize)
    }
    val videoList: Flow<PagingData<Trailer>> by lazy {
        getVideoPagerUseCase.execute(
            scope = viewModelScope, personId = application.selectedMovie!!.id,
            language = language, pageSize = pageSize)
    }
    val initDataDone = MutableLiveData(false)

    init {
        init(movie = application.selectedMovie)
    }

    private fun init(movie: Movie?) {
        viewModelScope.launch {
            movie?.let {
                movieDetail = getMovieDetailsUseCase.execute(movie = movie, language = language)
                initDataDone.value = true
            }
        }
    }

    fun insertFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        viewModelScope.launch {
            insertFavoriteMovieUseCase.execute(favorites = favorites, favoritesMovie = favoritesMovie)
        }
    }

    fun deleteFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
        viewModelScope.launch {
            deleteFavoriteMovieUseCase.execute(favorites = favorites, favoritesMovie = favoritesMovie)
        }
    }

    fun showPoster(show: Boolean) {
        movieDetail.showPoster = show
        initDataDone.value = true
    }
}
