package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.domain.model.Trailer
import com.themovieviewer.network.model.VideosDtoMapper
import com.themovieviewer.network.response.VideosResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG

class VideoDataSource(private val movieRepository: MovieRepository, private val videosDtoMapper: VideosDtoMapper, private val movieId: Int, private val language: String = "ko-KR") : PagingSource<Int, Trailer>() {

    override fun getRefreshKey(state: PagingState<Int, Trailer>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Trailer> {
        return try {
            val videosResponse: VideosResponse = movieRepository.getVideos(
                language = language,
                movie_id = movieId
            )

            for (movie in videosResponse.results) {
                Log.d(TAG, movie.toString())
            }

            LoadResult.Page(
                data = videosDtoMapper.toDomainList(videosResponse.results),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            Log.e(TAG, "launchJob: Exception: $e, ${e.cause}")
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
