package com.themovieviewer.core.model.data.network

import com.themovieviewer.core.data.network.model.CreditsCastCrewDto

data class MovieCreditsResponse(
    val id: String,
    val cast: List<CreditsCastCrewDto>,
    val crew: List<CreditsCastCrewDto>
)
