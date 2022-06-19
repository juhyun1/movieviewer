package com.themovieviewer.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.themovieviewer.core.datastore.util.decodeFromString
import com.themovieviewer.core.datastore.util.encodeToString
import com.themovieviewer.core.datastore.util.readValue
import com.themovieviewer.core.datastore.util.storeValue
import javax.inject.Inject
import timber.log.Timber

class PreferencesDataSource @Inject constructor(
//    private val userPreferences: DataStore<UserPreferences>,
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun setCategory(category: Category) {
        val value = encodeToString(value = category)
        Timber.d("Test : setCategory $value")
        dataStore.storeValue(key = PreferencesKeys.CATEGORY, value)

//        userPreferences.updateData(category = category)
    }

    suspend fun getCategory(): Category {
        val value: String = dataStore.readValue(key = PreferencesKeys.CATEGORY) ?: encodeToString(value = Category.NowPlaying)

        Timber.d("Test : value $value")
        Timber.d("Test : value2 ${encodeToString(value = Category.NowPlaying)}")
        return value.decodeFromString()
    }

    //region Protobuf
//    suspend fun setCategory(category: UserPreferences.Category) {
//        userPreferences.updateData(category = category)
//    }
//
//    suspend fun getUserPreferences(): UserPreferences = userPreferences.data.first()
    //endregion
}