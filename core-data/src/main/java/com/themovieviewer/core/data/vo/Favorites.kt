package com.themovieviewer.core.data.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorites(
    @PrimaryKey @ColumnInfo(name = "id") val kindId: Int,
    val name: String,
    val kind: String,
    val date: String?,
)
