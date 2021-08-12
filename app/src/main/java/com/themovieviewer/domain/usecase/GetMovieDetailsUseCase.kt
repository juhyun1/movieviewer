package com.themovieviewer.domain.usecase

import com.themovieviewer.domain.model.Movie
import com.themovieviewer.domain.model.MovieDetail
import com.themovieviewer.network.response.MovieDetailMapper
import com.themovieviewer.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository,
    private val movieDetailMapper: MovieDetailMapper
): UseCase {

    suspend fun execute(movie: Movie, language: String): MovieDetail {
        val movieDetailsResponse = movieRepository.getMovieDetails(language = language, movie_id = movie.id)
        val movieDetail = movieDetailMapper.mapToDomainModel(movieDetailsResponse)

        val videosResponse = movieRepository.getVideos(language = language, movie_id = movie.id)
        if (videosResponse.results.isNotEmpty()) {
            movieDetail.isTrailer = true
        }

        return movieDetail
    }
}
