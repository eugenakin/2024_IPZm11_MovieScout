package com.example.moviescout.data.api

import com.example.moviescout.data.models.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MoviesListResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesListResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MoviesListResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): MoviesListResponse
}