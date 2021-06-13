package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.PeopleMovieCreditsResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG
import java.util.stream.Collectors

class PeopleMovieCreditsDataSource(private val movieRepository: MovieRepository, private val movieDtoMapper: MovieDtoMapper, private val personId: Int, private val language: String) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val peopleMovieCreditsResponse: PeopleMovieCreditsResponse = movieRepository.getPeopleMovieCredits(
                language = language,
                person_id = personId,
            )

            for (movie in peopleMovieCreditsResponse.cast) {
                Log.d(TAG, movie.toString())
            }

            val cast = peopleMovieCreditsResponse.cast.stream().sorted { o1, o2 -> o1.compareTo(o2)}.collect(Collectors.toList())

            LoadResult.Page(
                data = movieDtoMapper.toDomainList(cast),
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
