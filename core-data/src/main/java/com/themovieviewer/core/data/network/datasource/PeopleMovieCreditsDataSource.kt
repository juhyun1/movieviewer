package com.themovieviewer.core.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.data.network.response.PeopleMovieCreditsResponse
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.repository.MovieRepository

class PeopleMovieCreditsDataSource(
    private val movieRepository: MovieRepository,
    private val movieDtoMapper: MovieDtoMapper,
    private val personId: Int,
    private val language: String) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val peopleMovieCreditsResponse: PeopleMovieCreditsResponse = movieRepository.getPeopleMovieCredits(
                language = language,
                person_id = personId,
            )

            val cast = peopleMovieCreditsResponse.cast.sortedWith { o1, o2 -> o1.compareTo(o2)}

            LoadResult.Page(
                data = movieDtoMapper.toDomainList(cast),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
