package com.themovieviewer.core.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.data.network.response.TopRatedResponse
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.repository.MovieRepository
import timber.log.Timber

class RecommendationsDataSource(private val movieRepository: MovieRepository, var movieId: Int = 0, private val language: String = "en_US") : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 0
            val requestPage = nextPageNumber + 1
            val pageData = movieRepository.getRecommendations(language = language, movieId = movieId, page = requestPage)
            LoadResult.Page(
                data = pageData.list,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < pageData.pageCount) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
