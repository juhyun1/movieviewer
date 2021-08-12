package com.themovieviewer.presentation.ui.people

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.themovieviewer.domain.model.CastCrew
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.domain.model.People
import com.themovieviewer.domain.usecase.GetActingDataPagerUseCase
import com.themovieviewer.domain.usecase.GetPeopleDetailsUseCase
import com.themovieviewer.domain.usecase.GetPeopleMovieCreditsPagerUseCase
import com.themovieviewer.network.response.PeopleMapper
import com.themovieviewer.presentation.BaseApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailsFragmentViewModel @Inject constructor(
    private val application: BaseApplication
) : ViewModel() {

    lateinit var model: People
    val pageSize = 100
    val language = application.language

    @Inject
    lateinit var getActingDataPagerUseCase: GetActingDataPagerUseCase
    @Inject
    lateinit var getPeopleMovieCreditsPagerUseCase: GetPeopleMovieCreditsPagerUseCase
    @Inject
    lateinit var getPeopleDetailsUseCase: GetPeopleDetailsUseCase
    @Inject
    lateinit var peopleMapper: PeopleMapper

    val initModelDone = MutableLiveData(false)

    val movieList: Flow<PagingData<Movie>> by lazy {
        getPeopleMovieCreditsPagerUseCase.execute(scope = viewModelScope, personId = application.selectedPerson!!.toInt(), language = application.language, pageSize = pageSize)
    }

    val actingList: Flow<PagingData<CastCrew>> by lazy {
        getActingDataPagerUseCase.execute(scope = viewModelScope, personId = application.selectedPerson!!.toInt(), language = application.language, pageSize = pageSize)
    }

    init {
        init(application.selectedPerson)
    }

    private fun init(person: Int?) {
        viewModelScope.launch {
            person?.let {
                model = getPeopleDetailsUseCase.execute(personId = person, language = language)
                initModelDone.value = true
            }
        }
    }
}