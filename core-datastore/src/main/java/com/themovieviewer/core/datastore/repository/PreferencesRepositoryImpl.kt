package com.themovieviewer.core.datastore.repository

import com.themovieviewer.core.datastore.PreferencesDataSource
import com.themovieviewer.core.datastore.UserPreferences
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val dateSource: PreferencesDataSource
) : PreferencesRepository {
    override suspend fun setCategory(category: UserPreferences.Category) {
        dateSource.setCategory(category = category)
    }

    override suspend fun getCategory(): UserPreferences.Category = dateSource.getCategory()
}
