package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.network.model.MovieDtoMapper
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
//
//                val movieDetailsResponse: MovieDetailsResponse = movieRepository.getMovieDetails(
//                    language = language,
//                    movie_id = movie.id
//                )
//
//                Log.d(TAG, "budget : " + movieDetailsResponse.budget + " revenue : " + movieDetailsResponse.revenue)
//
//                val movieCreditsResponse: MovieCreditsResponse = movieRepository.getMovieCredits(
//                    language = language,
//                    movie_id = movie.id
//                )
//                Log.d(TAG, "Name : " + movieCreditsResponse.cast[0].name)
//
//                val peopleDetailsResponse: PeopleDetailsResponse = movieRepository.getPeopleDetails(
//                    language = language,
//                    person_id = movieCreditsResponse.cast[0].id
//                )
//                Log.d(TAG, "Name : " + peopleDetailsResponse.name + " birthday " + peopleDetailsResponse.birthday)
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