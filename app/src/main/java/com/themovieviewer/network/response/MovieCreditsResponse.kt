package com.themovieviewer.network.response

import com.themovieviewer.network.model.CreditsCastCrewDto

data class MovieCreditsResponse(
    val id: String,
    val cast: List<CreditsCastCrewDto>,
    val crew: List<CreditsCastCrewDto>
)