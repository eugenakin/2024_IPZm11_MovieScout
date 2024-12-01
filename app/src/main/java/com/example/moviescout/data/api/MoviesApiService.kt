package com.example.moviescout.data.api

import com.example.moviescout.data.models.MovieDetails
import com.example.moviescout.data.models.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: String): MovieDetails

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): MoviesListResponse
}