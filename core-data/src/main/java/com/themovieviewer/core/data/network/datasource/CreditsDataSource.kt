package com.themovieviewer.core.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.core.model.data.CreditsCastCrew
import com.themovieviewer.core.data.repository.MovieRepository

class CreditsDataSource(private val movieRepository: MovieRepository, var movieId: Int = 0, var language: String = "en_US") : PagingSource<Int, CreditsCastCrew>() {

    override fun getRefreshKey(state: PagingState<Int, CreditsCastCrew>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CreditsCastCrew> {
        return try {

            val pageData = movieRepository.getMovieCredits(language = language, movie_id = movieId)

            LoadResult.Page(
                data = pageData.list,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}