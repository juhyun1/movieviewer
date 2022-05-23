package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.data.network.response.TopRatedResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG

class RecommendationsDataSource(private val movieRepository: MovieRepository, private val movieDtoMapper: MovieDtoMapper, private val movieId: Int, private val language: String) : PagingSource<Int, Movie>() {

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
            val movieListResponse: TopRatedResponse = movieRepository.getRecommendations(
                language = language,
                page = requestPage,
                movieId = movieId
            )

            val list = movieListResponse.results.sortedWith { o1, o2 -> o1.compareTo(o2) }

            LoadResult.Page(
                data = movieDtoMapper.toDomainList(list),
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < movieListResponse.total_pages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            Log.e(TAG, "launchJob: Exception: $e, ${e.cause}")
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
