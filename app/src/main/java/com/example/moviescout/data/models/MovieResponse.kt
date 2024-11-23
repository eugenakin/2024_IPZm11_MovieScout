package com.example.moviescout.data.models

data class Dates (
    val maximum: String,
    val minimum: String,
)

data class Movie (
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int,
)

data class MoviesListResponse (
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
)