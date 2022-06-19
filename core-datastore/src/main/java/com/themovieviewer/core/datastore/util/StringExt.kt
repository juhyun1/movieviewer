package com.themovieviewer.core.datastore.util

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> encodeToString(value: T): String {
    return Json.encodeToString(value)
}
inline fun <reified T> String.decodeFromString(): T {
    return Json.decodeFromString(this)
}