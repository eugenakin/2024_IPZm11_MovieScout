package com.example.moviescout.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.moviescout.data.api.RetrofitInstance
import com.example.moviescout.data.models.Movie
import com.example.moviescout.data.models.MoviesListResponse

enum class MoviesCategory {
    NOW_PLAYING,
    POPULAR,
    TOP_RATED,
    UPCOMING
}

class MoviesRepository {
    private suspend fun fetchNowPlayingMovies(): List<Movie> {
        return try {
            val response: MoviesListResponse = RetrofitInstance.api.getNowPlayingMovies()
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching now playing movies", e)
            emptyList()
        }
    }

    private suspend fun fetchPopularMovies(): List<Movie> {
        return try {
            val response: MoviesListResponse = RetrofitInstance.api.getPopularMovies()
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching popular movies", e)
            emptyList()
        }
    }

    private suspend fun fetchTopRatedMovies(): List<Movie> {
        return try {
            val response: MoviesListResponse = RetrofitInstance.api.getTopRatedMovies()
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching top rated movies", e)
            emptyList()
        }
    }

    private suspend fun fetchUpcomingMovies(): List<Movie> {
        return try {
            val response: MoviesListResponse = RetrofitInstance.api.getUpcomingMovies()
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching upcoming movies", e)
            emptyList()
        }
    }

    suspend fun fetchMoviesByCategory(category: MoviesCategory): List<Movie> {
        return when(category) {
            MoviesCategory.NOW_PLAYING -> fetchNowPlayingMovies()
            MoviesCategory.POPULAR -> fetchPopularMovies()
            MoviesCategory.TOP_RATED -> fetchTopRatedMovies()
            MoviesCategory.UPCOMING -> fetchUpcomingMovies()
        }
    }
}
