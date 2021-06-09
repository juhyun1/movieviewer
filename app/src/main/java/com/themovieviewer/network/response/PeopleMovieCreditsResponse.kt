package com.themovieviewer.network.response

import com.themovieviewer.network.model.PeopleMovieCreditsCastDto
import com.themovieviewer.network.model.PeopleMovieCreditsCrewDto

data class PeopleMovieCreditsResponse (
    val cast: List<PeopleMovieCreditsCastDto>,
    val crew: List<PeopleMovieCreditsCrewDto>,
    val id: Int
)