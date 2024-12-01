package com.example.moviescout.utils

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatReleaseDate(releaseDate: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        try {
            val parsedDate = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
            parsedDate.format(formatter)
        } catch (e: Exception) {
            releaseDate
        }
    } else {
        releaseDate
    }
}
