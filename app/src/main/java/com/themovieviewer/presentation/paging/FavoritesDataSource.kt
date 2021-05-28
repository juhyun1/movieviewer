package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.data.DaoMapper
import com.themovieviewer.data.FavoritesMovie
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.repository.FavoritesMovieRepository
import com.themovieviewer.repository.FavoritesRepository
import com.themovieviewer.util.TAG

class FavoritesDataSource(private val favoritesRepository: FavoritesRepository, private val favoritesMovieRepository: FavoritesMovieRepository, private val daoMapper: DaoMapper) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {

            val favoritesList =  favoritesRepository.getFavorites()

            val list: ArrayList<FavoritesMovie> = ArrayList()
            for(favorite in favoritesList) {
                list.add(favoritesMovieRepository.getFavoritesMovies(favorite.kindId))
            }

            LoadResult.Page(
                data = daoMapper.toDomainList(list),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}