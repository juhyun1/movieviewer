package com.themovieviewer.presentation.ui.people

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieviewer.data.vo.Favorites
import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.network.model.MovieDetailsResponse
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.PeopleDetailsResponse
import com.themovieviewer.presentation.BaseApplication
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import com.themovieviewer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class PeopleDetailsFragmentViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper,
    private val application: BaseApplication,
    private val favoritesRepository: FavoritesRepository,
    private val favoritesMovieRepository: FavoritesMovieRepository
) : ViewModel() {


    val personalInfo: MutableLiveData<String> = MutableLiveData("")
    val knownFor: MutableLiveData<String> = MutableLiveData("")
    val gender: MutableLiveData<Int> = MutableLiveData(2)
    val knownCredits: MutableLiveData<String> = MutableLiveData("")
    val birthday: MutableLiveData<String> = MutableLiveData("")
    val placeOfBirths: MutableLiveData<String> = MutableLiveData("")
    val biography: MutableLiveData<String> = MutableLiveData("")
    val acting: MutableLiveData<String> = MutableLiveData("")

    init {
        init(application.selectedPerson)
    }

    fun init(person: Int?) {
        val language = "ko-KR"
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            person?.let {
                val peopleDetailsResponse: PeopleDetailsResponse = movieRepository.getPeopleDetails(
                    language = language,
                    person_id = person
                )

                peopleDetailsResponse.let {
                    knownFor.value = it.known_for_department
//                    knownCredits.value = it.known_for_department
                    gender.value = it.gender
                    birthday.value = it.birthday
                    placeOfBirths.value = it.place_of_birth
                    biography.value = it.biography
                }
            }
        }
    }
}