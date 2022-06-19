package com.themovieviewer.core.datastore.repository

import com.themovieviewer.core.datastore.Category
import com.themovieviewer.core.datastore.PreferencesDataSource
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val dateSource: PreferencesDataSource
) : PreferencesRepository {
    override suspend fun setCategory(category: Category) {
        dateSource.setCategory(category = category)
    }

    override suspend fun getCategory(): Category {
        return dateSource.getCategory()
    }
}
