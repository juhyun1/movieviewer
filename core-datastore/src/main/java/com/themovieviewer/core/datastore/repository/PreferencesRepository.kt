package com.themovieviewer.core.datastore.repository

import com.themovieviewer.core.datastore.Category


interface PreferencesRepository {

    suspend fun setCategory(category: Category)
    suspend fun getCategory(): Category
}