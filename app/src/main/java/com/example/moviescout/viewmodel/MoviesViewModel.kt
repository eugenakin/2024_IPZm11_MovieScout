package com.example.moviescout.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescout.data.models.Movie
import com.example.moviescout.data.repository.MoviesCategory
import com.example.moviescout.data.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MoviesViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies
    private val moviesRepository = MoviesRepository()
    var isLoading by mutableStateOf(false)

    init {
        fetchMoviesByCategory(MoviesCategory.NOW_PLAYING)
    }

    fun fetchMoviesByCategory(category: MoviesCategory) {
        isLoading = true
        viewModelScope.launch {
            val moviesList = moviesRepository.fetchMoviesByCategory(category)
            _movies.value = moviesList
            isLoading = false
        }
    }
}