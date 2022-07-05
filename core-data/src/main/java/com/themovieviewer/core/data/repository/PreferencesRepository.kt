package com.themovieviewer.core.data.repository

import com.themovieviewer.core.datastore.Category
import com.themovieviewer.core.datastore.Language


interface PreferencesRepository {

    suspend fun setCategory(category: Category)
    suspend fun getCategory(): Category

    suspend fun setLanguage(language: Language)
    suspend fun getLanguage(): Language
}