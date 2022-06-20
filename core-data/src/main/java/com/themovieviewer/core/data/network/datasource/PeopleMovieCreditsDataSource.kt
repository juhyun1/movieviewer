package com.themovieviewer.core.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.core.data.network.model.MovieDtoMapper
import com.themovieviewer.core.data.network.response.PeopleMovieCreditsResponse
import com.themovieviewer.core.model.data.CastCrew
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.repository.MovieRepository

class PeopleMovieCreditsDataSource(
    private val movieRepository: MovieRepository,
    var personId: Int = 0,
    var language: String = "en_US") : PagingSource<Int, CastCrew>() {

    override fun getRefreshKey(state: PagingState<Int, CastCrew>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CastCrew> {
        return try {
            val pageData = movieRepository.getPeopleMovieCredits(
                language = language,
                person_id = personId,
            )

            LoadResult.Page(
                data = pageData.list.sortedByDescending { it.release_date },
                prevKey = null,
                nextKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
