package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.momain.model.Movie
import com.themovieviewer.network.model.MovieDetailsDto
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.MovieDetailsResponse
import com.themovieviewer.network.response.TopRatedResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG

class NowPlayingDataSource(private val movieRepository: MovieRepository, private val movieDtoMapper: MovieDtoMapper) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 0
            val requestPage = nextPageNumber + 1
            val language = "en-US"
            val movieListResponse: TopRatedResponse = movieRepository.getNowPlaying(
                language = language,
                page = requestPage
            )


            for (movie in movieListResponse.results) {
                Log.d(TAG, movie.toString())

//                val movieDetailsResponse: MovieDetailsDto = movieRepository.getMovieDetails(
//                    language = language,
//                    movie_id = movie.id
//                )
//                Log.d(TAG, movieDetailsResponse.toString())
            }

            LoadResult.Page(
                data = movieDtoMapper.toDomainList(movieListResponse.results),
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < movieListResponse.total_pages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}