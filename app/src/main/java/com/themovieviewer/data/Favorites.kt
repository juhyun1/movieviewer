package com.themovieviewer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorites(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val favorites: Int = 0,
    val name: String,
    val kind: String,
    val kindId: Int,
    val date: String,
)