package com.themovieviewer.core.model.data.network

import com.themovieviewer.core.data.network.model.PeopleMovieCreditsCastDto
import com.themovieviewer.core.data.network.model.PeopleMovieCreditsCrewDto

data class PeopleMovieCreditsResponse (
    val cast: List<PeopleMovieCreditsCastDto>,
    val crew: List<PeopleMovieCreditsCrewDto>,
    val id: Int
)