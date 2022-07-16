package com.themovieviewer.feature.movielist.model

sealed interface PreferenceState {
    object Show : PreferenceState
    object Hide : PreferenceState
}