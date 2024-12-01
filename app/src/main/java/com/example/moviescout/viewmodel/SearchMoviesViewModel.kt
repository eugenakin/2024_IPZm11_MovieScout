package com.example.moviescout.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescout.data.models.Movie
import com.example.moviescout.data.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val moviesRepository = MoviesRepository()
    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())

    val searchResults: StateFlow<List<Movie>> get() = _searchResults
    var isLoading by mutableStateOf(true)

    init {
        fetchAllMovies()
    }

    private fun fetchAllMovies() {

        viewModelScope.launch {
            val allMovies = moviesRepository.fetchAllMovies()
            _searchResults.value = allMovies
            isLoading = false
        }
    }

    fun searchMovies(query: String) {

        viewModelScope.launch {
            val results = moviesRepository.searchMovies(query)
            _searchResults.value = results
            isLoading = false
        }
    }
}
