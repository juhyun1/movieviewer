package com.themovieviewer.feature.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieviewer.core.data.network.datasource.ActingDataSource
import com.themovieviewer.core.data.network.datasource.PeopleMovieCreditsDataSource
import com.themovieviewer.core.datastore.Language
import com.themovieviewer.core.datastore.repository.PreferencesRepository
import com.themovieviewer.core.model.data.CastCrew
import com.themovieviewer.core.model.data.People
import com.themovieviewer.core.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val peopleMovieCreditsDataSource: PeopleMovieCreditsDataSource,
    private val actingDataSource: ActingDataSource,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _peopleDetail = MutableLiveData<People>()
    val peopleDetail: LiveData<People> get() = _peopleDetail

    private val _castList = MutableLiveData<List<CastCrew>>()
    val castList: LiveData<List<CastCrew>> get() = _castList

    private var personID = 0
    private var locale: Language = runBlocking {
        preferencesRepository.getLanguage()
    }

    fun getPeopleMovieCreditsDataSource(): PeopleMovieCreditsDataSource = peopleMovieCreditsDataSource.apply {
        this.personId = personID
        this.language = locale.getLocale()
    }

    fun getActingData() {
        viewModelScope.launch {
            val data = repository.getPeopleMovieCredits(language = locale.getLocale(), person_id = personID)
            _castList.value = data.list
        }
    }

    fun getPeopleInfo(personId: Int) {
        this.personID = personId
        viewModelScope.launch {
            val detail = repository.getPeopleDetails(language = locale.getLocale(), person_id = personId)
            _peopleDetail.value = detail
        }
    }
}