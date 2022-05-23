package com.themovieviewer.core.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.core.data.DaoMapper
import com.themovieviewer.core.data.repository.FavoritesMovieRepository
import com.themovieviewer.core.data.repository.FavoritesRepository
import com.themovieviewer.core.data.vo.FavoritesMovie
import com.themovieviewer.core.model.data.Movie

class FavoritesDataSource(private val favoritesRepository: FavoritesRepository, private val favoritesMovieRepository: FavoritesMovieRepository, private val daoMapper: DaoMapper) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {

            val favoritesList = favoritesRepository.getFavorites()

            val list: ArrayList<FavoritesMovie> = ArrayList()

            favoritesList.forEach { favorite ->
                list.add(favoritesMovieRepository.getFavoritesMovies(favorite.kindId))
            }

            LoadResult.Page(
                data = daoMapper.toDomainList(list),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
