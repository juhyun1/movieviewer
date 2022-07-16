package com.themovieviewer

import android.app.Application
import com.themovieviewer.core.model.data.Movie
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {
    var selectedMovie: Movie? = null
    var selectedPerson: Int? = null
    var language: String = "en-US"

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}
