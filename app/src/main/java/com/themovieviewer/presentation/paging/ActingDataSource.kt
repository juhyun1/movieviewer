package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.domain.model.CastCrew
import com.themovieviewer.network.model.CastCrewDtoMapper
import com.themovieviewer.network.response.PeopleMovieCreditsResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG

class ActingDataSource(private val movieRepository: MovieRepository, private val castCrewDtoMapper: CastCrewDtoMapper, private val personId: Int, private val language: String = "ko-KR") : PagingSource<Int, CastCrew>() {

    override fun getRefreshKey(state: PagingState<Int, CastCrew>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CastCrew> {
        return try {
            val peopleMovieCreditsResponse: PeopleMovieCreditsResponse = movieRepository.getPeopleMovieCredits(
                language = language,
                person_id = personId,
            )

            for (cast in peopleMovieCreditsResponse.cast) {
                Log.d(TAG, cast.toString())
            }

            LoadResult.Page(
                data = castCrewDtoMapper.toDomainList(peopleMovieCreditsResponse.cast),
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