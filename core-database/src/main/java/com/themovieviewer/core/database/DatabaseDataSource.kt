package com.themovieviewer.core.database

import com.themovieviewer.core.database.dao.FavoritesDao
import com.themovieviewer.core.database.dao.FavoritesMovieDao
import javax.inject.Inject

class DatabaseDataSource @Inject constructor(
    private val favoritesMovieDao: FavoritesMovieDao,
    private val favoritesDao: FavoritesDao,
) {


}