package com.themovieviewer.core.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.core.data.network.model.VideosDtoMapper
import com.themovieviewer.core.data.network.response.VideosResponse
import com.themovieviewer.core.model.data.Trailer
import com.themovieviewer.core.model.repository.MovieRepository
import timber.log.Timber

class VideoDataSource(private val movieRepository: MovieRepository, var movieId: Int = 0, var language: String = "en_US") : PagingSource<Int, Trailer>() {

    override fun getRefreshKey(state: PagingState<Int, Trailer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Trailer> {
        return try {
            val pageData = movieRepository.getVideos(language = language, movie_id = movieId)
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
