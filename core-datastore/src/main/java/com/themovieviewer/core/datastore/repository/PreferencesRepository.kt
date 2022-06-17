package com.themovieviewer.core.datastore.repository

import com.themovieviewer.core.datastore.UserPreferences

interface PreferencesRepository {

    suspend fun setCategory(category: UserPreferences.Category)
    suspend fun getCategory(): UserPreferences.Category
}