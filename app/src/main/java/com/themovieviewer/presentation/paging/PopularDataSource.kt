package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.TopRatedResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG

class PopularDataSource(private val movieRepository: MovieRepository, private val movieDtoMapper: MovieDtoMapper, private val language: String = "ko-KR") : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 0
            val requestPage = nextPageNumber + 1

            val movieListResponse: TopRatedResponse = movieRepository.getPopular(
                language = language,
                page = requestPage
            )

            LoadResult.Page(
                data = movieDtoMapper.toDomainList(movieListResponse.results),
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
