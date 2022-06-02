package com.themovieviewer.feature.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieviewer.core.data.network.datasource.PeopleMovieCreditsDataSource
import com.themovieviewer.core.model.data.People
import com.themovieviewer.core.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val peopleMovieCreditsDataSource: PeopleMovieCreditsDataSource
) : ViewModel() {
    private val _peopleDetail = MutableLiveData<People>()
    val peopleDetail: LiveData<People> get() = _peopleDetail
    private var personID = 0

    fun getPeopleMovieCreditsDataSource(): PeopleMovieCreditsDataSource = peopleMovieCreditsDataSource.apply { this.personId = personID }

    fun getPeopleInfo(personId: Int) {
        this.personID = personId
        viewModelScope.launch {
            val detail = repository.getPeopleDetails(person_id = personId)
            _peopleDetail.value = detail
        }
    }
}