package com.themovieviewer.presentation

import android.app.Application
import com.themovieviewer.domain.model.Movie
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    var selectedMovie: Movie? = null
    var selectedPerson: Int? = null
    var language: String = "en-US"
}
