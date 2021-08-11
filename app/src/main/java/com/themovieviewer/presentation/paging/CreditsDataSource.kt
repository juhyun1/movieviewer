package com.themovieviewer.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.themovieviewer.network.model.CreditsCastCrewDto
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.MovieCreditsResponse
import com.themovieviewer.repository.MovieRepository
import com.themovieviewer.util.TAG

class CreditsDataSource(private val movieRepository: MovieRepository, private val movieDtoMapper: MovieDtoMapper, private val movieId: Int, private val language: String) : PagingSource<Int, CreditsCastCrewDto>() {

    override fun getRefreshKey(state: PagingState<Int, CreditsCastCrewDto>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CreditsCastCrewDto> {
        return try {
            val movieCreditsResponse: MovieCreditsResponse = movieRepository.getMovieCredits(
                language = language,
                movie_id = movieId
            )

            LoadResult.Page(
                data = movieCreditsResponse.cast,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}