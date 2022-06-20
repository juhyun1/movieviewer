package com.themovieviewer.core.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val CATEGORY = stringPreferencesKey("category")
    val LANGUAGE = stringPreferencesKey("language")
}