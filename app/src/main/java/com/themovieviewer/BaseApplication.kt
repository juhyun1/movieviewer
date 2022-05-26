package com.themovieviewer

import android.app.Application
import com.themovieviewer.core.model.data.Movie
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    var selectedMovie: Movie? = null
    var selectedPerson: Int? = null
    var language: String = "en-US"
}
