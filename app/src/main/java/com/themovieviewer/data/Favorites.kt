package com.themovieviewer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorites(
    @PrimaryKey @ColumnInfo(name = "id") val favorites: String,
    val name: String,
    val kind: String,
    val kindId: String,
    val date: String,
)