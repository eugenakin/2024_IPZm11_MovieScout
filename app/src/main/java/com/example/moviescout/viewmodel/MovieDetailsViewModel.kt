package com.example.moviescout.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescout.data.models.MovieDetails
import com.example.moviescout.data.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MovieDetailsViewModel(private val movieId: String) : ViewModel() {
    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> get() = _movieDetails
    private val moviesRepository = MoviesRepository()
    var isLoading by mutableStateOf(false)

    init {
        fetchMovieById(movieId)
    }

    private fun fetchMovieById(id: String) {
        isLoading = true
        viewModelScope.launch {
            val movie = moviesRepository.getMovieById(id)
            _movieDetails.value = movie
            isLoading = false
        }
    }
}