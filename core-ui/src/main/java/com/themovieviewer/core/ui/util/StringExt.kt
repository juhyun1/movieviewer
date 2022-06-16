package com.themovieviewer.core.ui.util

import com.themovieviewer.core.model.data.CastCrew


fun String.imagePath(): String = "https://image.tmdb.org/t/p/w500$this"
fun String.thumbnailPath() = "https://img.youtube.com/vi/$this/sddefault.jpg"
fun CastCrew.actingText(): String {
    return if (this.release_date == null) {
        var string = "-"
        string = "$string ${this.title}"
        if (this.character.isNotBlank()) {
            string = "$string as ${this.character}"
        }
        string
    } else {
        val date = this.release_date!!
        var string = if (date.indexOf("-") != -1) {
            date.substring(0, date.indexOf("-"))
        } else {
            date
        }
        string = "$string ${this.title}"
        if (this.character.isNotBlank()) {
            string = "$string as ${this.character}"
        }
        string
    }
}
