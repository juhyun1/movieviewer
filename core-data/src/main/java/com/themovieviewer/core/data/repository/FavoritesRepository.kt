package com.themovieviewer.core.data.repository

import androidx.annotation.WorkerThread
import com.themovieviewer.core.data.DaoMapper
import com.themovieviewer.core.data.FavoritesDao
import com.themovieviewer.core.model.data.Favorites
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val favoritesDao: FavoritesDao,
    ) {

    fun getFavorites() = favoritesDao.getFavorites()

    @WorkerThread
    suspend fun deleteFavorites(favorites: Favorites) {

//        mapper.mapFromDomainModel(favorites)
//        favoritesDao.delete(favorites)
    }

    @WorkerThread
    suspend fun insertFavorites(favorites: Favorites) {
//        favoritesDao.insert(favorites)
    }
}
