package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.core.model.data.CastCrew
import com.themovieviewer.network.model.CastCrewDtoMapper
import com.themovieviewer.network.response.PeopleMovieCreditsResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG

class ActingDataSource(
    private val movieRepository: MovieRepository,
    private val castCrewDtoMapper: CastCrewDtoMapper,
    private val personId: Int,
    private val language: String) : PagingSource<Int, CastCrew>() {

    override fun getRefreshKey(state: PagingState<Int, CastCrew>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CastCrew> {
        return try {
            val peopleMovieCreditsResponse: PeopleMovieCreditsResponse = movieRepository.getPeopleMovieCredits(
                language = language,
                person_id = personId,
            )

            val cast = peopleMovieCreditsResponse.cast.sortedWith { o1, o2 ->
                o1.compareTo(o2)
            }

            LoadResult.Page(
                data = castCrewDtoMapper.toDomainList(cast),
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