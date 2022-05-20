package com.themovieviewer.core.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailer (
    val id: String,
    val iso_6391_1: String?,
    val iso_3166_1: String?,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
) : Parcelable