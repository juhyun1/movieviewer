package com.themovieviewer.core.data.repository

import androidx.annotation.WorkerThread
import com.themovieviewer.core.data.FavoritesDao
import com.themovieviewer.core.data.vo.Favorites
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val favoritesDao: FavoritesDao) {

    fun getFavorites() = favoritesDao.getFavorites()

    @WorkerThread
    suspend fun deleteFavorites(favorites: Favorites) = favoritesDao.delete(favorites)

    @WorkerThread
    suspend fun insertFavorites(favorites: Favorites) = favoritesDao.insert(favorites)
}
