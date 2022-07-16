package com.themovieviewer.core.data.repository

import com.themovieviewer.core.datastore.Category
import com.themovieviewer.core.datastore.Language
import com.themovieviewer.core.datastore.PreferencesDataSource
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val dateSource: PreferencesDataSource
) : PreferencesRepository {
    override suspend fun setCategory(category: Category) {
        dateSource.setCategory(category = category)
    }

    override suspend fun getCategory(): Category = dateSource.getCategory()

    override suspend fun setLanguage(language: Language) {
        dateSource.setLanguage(language = language)
    }
    override suspend fun getLanguage(): Language = dateSource.getLanguage()
}
