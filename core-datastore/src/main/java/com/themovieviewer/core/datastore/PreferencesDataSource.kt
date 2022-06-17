package com.themovieviewer.core.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {

    suspend fun setCategory(category: UserPreferences.Category) {
        userPreferences.updateData { currentPreferences ->
            currentPreferences.toBuilder().setCategory(category).build()
        }
    }

    suspend fun getCategory(): UserPreferences.Category {
        val result = userPreferences.data.map { it.category }.first()
        return if (result == UserPreferences.Category.UNRECOGNIZED) {
            UserPreferences.Category.NOW_PLAYING
        } else {
            result
        }
    }

}