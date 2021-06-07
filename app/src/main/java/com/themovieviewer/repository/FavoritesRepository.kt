package com.themovieviewer.repository

import androidx.annotation.WorkerThread
import com.themovieviewer.data.FavoritesDao
import com.themovieviewer.data.vo.Favorites
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val favoritesDao: FavoritesDao) {

    fun getFavorites() = favoritesDao.getFavorites()

    @WorkerThread
    suspend fun deleteFavorites(favorites: Favorites) = favoritesDao.delete(favorites)

    @WorkerThread
    suspend fun insertFavorites(favorites: Favorites) = favoritesDao.insert(favorites)
}
