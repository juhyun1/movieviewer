package com.themovieviewer.presentation.ui.people

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.themovieviewer.network.model.CastCrewDtoMapper
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.PeopleDetailsResponse
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.presentation.paging.ActingDataSource
import com.themovieviewer.presentation.paging.PeopleMovieCreditsDataSource
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import com.themovieviewer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailsFragmentViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper,
    private var castCrewDtoMapper: CastCrewDtoMapper,
    private val application: BaseApplication,
    private val favoritesRepository: FavoritesRepository,
    private val favoritesMovieRepository: FavoritesMovieRepository
) : ViewModel() {

    val profileImage: MutableLiveData<String> = MutableLiveData("")
    val knownFor: MutableLiveData<String> = MutableLiveData("")
    val gender: MutableLiveData<String> = MutableLiveData("")
    val birthday: MutableLiveData<String> = MutableLiveData("")
    val placeOfBirths: MutableLiveData<String> = MutableLiveData("")
    val biography: MutableLiveData<String> = MutableLiveData("")
    val acting: MutableLiveData<String> = MutableLiveData("")
    val name: MutableLiveData<String> = MutableLiveData("")

    val movieList = Pager(PagingConfig(pageSize = 100)) {
        PeopleMovieCreditsDataSource(movieRepository, movieDtoMapper, application.selectedPerson!!.toInt())
    }.flow.cachedIn(viewModelScope)

    val actingList = Pager(PagingConfig(pageSize = 100)) {
        ActingDataSource(movieRepository, castCrewDtoMapper, application.selectedPerson!!.toInt())
    }.flow.cachedIn(viewModelScope)

    init {
        init(application.selectedPerson)
    }

    fun init(person: Int?) {
        val language = "en-US"
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            person?.let {
                val peopleDetailsResponse: PeopleDetailsResponse = movieRepository.getPeopleDetails(
                    language = language,
                    person_id = person
                )

                peopleDetailsResponse.let {
                    name.value = it.name
                    knownFor.value = it.known_for_department
                    when(it.gender) {
                        1 -> gender.value = "Woman"
                        2 -> gender.value = "Man"
                    }
                    birthday.value = it.birthday
                    placeOfBirths.value = it.place_of_birth
                    biography.value = it.biography

                    val base = "https://image.tmdb.org/t/p/w500"
                    profileImage.value = base + it.profile_path
                }
            }
        }
    }
}