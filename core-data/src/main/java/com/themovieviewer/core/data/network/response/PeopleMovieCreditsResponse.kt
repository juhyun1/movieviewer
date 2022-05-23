package com.themovieviewer.core.data.network.response

import com.themovieviewer.core.data.network.model.PeopleMovieCreditsCastDto
import com.themovieviewer.core.data.network.model.PeopleMovieCreditsCrewDto

data class PeopleMovieCreditsResponse (
    val cast: List<PeopleMovieCreditsCastDto>,
    val crew: List<PeopleMovieCreditsCrewDto>,
    val id: Int
)