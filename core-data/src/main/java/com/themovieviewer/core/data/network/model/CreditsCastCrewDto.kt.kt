package com.themovieviewer.core.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditsCastCrewDto(
    val adult: Boolean,
    val gender: Int?,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Float,
    val profile_path: String?,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val order: Int,

    // the bellow is crew attribute
    val department: String,
    val job: String
) : Parcelable
