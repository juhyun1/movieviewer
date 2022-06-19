package com.themovieviewer.core.datastore.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException

suspend inline fun <T : Any> DataStore<Preferences>.readValue(key: Preferences.Key<T>, defaultValue: T): T {
    return data.catch { recoverOrThrow(it) }.map { it[key] }.firstOrNull() ?: defaultValue
}

suspend inline fun <T : Any> DataStore<Preferences>.readValue(key: Preferences.Key<T>): T? {
    return data.catch { recoverOrThrow(it) }.map { it[key] }.firstOrNull()
}

suspend fun FlowCollector<Preferences>.recoverOrThrow(throwable: Throwable) {
    if (throwable is IOException) {
        emit(emptyPreferences())
    } else {
        throw throwable
    }
}


suspend inline fun <T : Any> DataStore<Preferences>.storeValue(key: Preferences.Key<T>, value: T?) {
    edit { preferences ->
        if (value == null) {
            preferences.remove(key)
        } else {
            preferences[key] = value
        }
    }
}


//region Protobuf
//suspend fun DataStore<UserPreferences>.updateData(category: UserPreferences.Category) {
//    this.updateData { currentPreferences ->
//        currentPreferences.toBuilder().setCategory(category).build()
//    }
//}
//
//suspend fun DataStore<UserPreferences>.getCategory(): UserPreferences.Category = this.data.map { it.category }.first()
//endregion
