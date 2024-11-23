package com.example.moviescout.data.repository

import android.util.Log
import com.example.moviescout.data.api.RetrofitInstance
import com.example.moviescout.data.models.Movie
import com.example.moviescout.data.models.MoviesListResponse

class MealRepository {
    suspend fun fetchNowPlayingMovies(): List<Movie> {
        return try {
            val response: MoviesListResponse = RetrofitInstance.api.getNowPlayingMovies()
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching now playing movies", e)
            emptyList()
        }
    }

    suspend fun fetchPopularMovies(): List<Movie> {
        return try {
            val response: MoviesListResponse = RetrofitInstance.api.getPopularMovies()
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching popular movies", e)
            emptyList()
        }
    }

    suspend fun fetchTopRatedMovies(): List<Movie> {
        return try {
            val response: MoviesListResponse = RetrofitInstance.api.getTopRatedMovies()
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching top rated movies", e)
            emptyList()
        }
    }

    suspend fun fetchUpcomingMovies(): List<Movie> {
        return try {
            val response: MoviesListResponse = RetrofitInstance.api.getUpcomingMovies()
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching upcoming movies", e)
            emptyList()
        }
    }
}
