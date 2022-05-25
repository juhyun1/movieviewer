package com.themovieviewer.presentation.ui.moviedetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsFragmentViewModel @Inject constructor(
//    private val application: BaseApplication,
//    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
//    private val getCreditsPagerUseCase: GetCreditsPagerUseCase,
//    private val getRecommendationsPagerUseCase: GetRecommendationsPagerUseCase,
//    private val getVideoPagerUseCase: GetVideoPagerUseCase,
//    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase,
//    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
) : ViewModel() {
//
//    private val pageSize = 100
//    lateinit var movieDetail: MovieDetail
//    private val language = application.language
//
//    val creditsList: Flow<PagingData<CreditsCastCrewDto>> = Pager(PagingConfig(pageSize = pageSize)) {
//        getCreditsPagerUseCase(movieId = application.selectedMovie!!.id, language = language) as CreditsDataSource
//    }.flow.cachedIn(viewModelScope)
//
//    val movieList: Flow<PagingData<Movie>> by lazy {
//        getRecommendationsPagerUseCase(
//            scope = viewModelScope, personId = application.selectedMovie!!.id,
//            language = language, pageSize = pageSize)
//    }
//    val videoList: Flow<PagingData<Trailer>> by lazy {
//        getVideoPagerUseCase(
//            scope = viewModelScope, personId = application.selectedMovie!!.id,
//            language = language, pageSize = pageSize)
//    }
//    val initDataDone = MutableLiveData(false)
//
//    init {
//        init(movie = application.selectedMovie)
//    }
//
//    private fun init(movie: Movie?) {
//        viewModelScope.launch {
//            movie?.let {
//                movieDetail = getMovieDetailsUseCase.execute(movie = movie, language = language)
//                initDataDone.value = true
//            }
//        }
//    }
//
//    fun insertFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
//        viewModelScope.launch {
//            insertFavoriteMovieUseCase.execute(favorites = favorites, favoritesMovie = favoritesMovie)
//        }
//    }
//
//    fun deleteFavoriteMovie(favorites: Favorites, favoritesMovie: FavoritesMovie) {
//        viewModelScope.launch {
//            deleteFavoriteMovieUseCase.execute(favorites = favorites, favoritesMovie = favoritesMovie)
//        }
//    }
//
//    fun showPoster(show: Boolean) {
//        movieDetail.showPoster = show
//        initDataDone.value = true
//    }
}
