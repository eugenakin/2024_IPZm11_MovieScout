package com.example.moviescout.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.moviescout.viewmodel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MoviesSearchScreen(navController: NavHostController, innerPadding: PaddingValues) {
    val moviesSearchViewModel: SearchViewModel = viewModel()
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by moviesSearchViewModel.searchResults.collectAsState()
    val isLoading = moviesSearchViewModel.isLoading
    var debounceJob by remember { mutableStateOf<Job?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        OutlinedTextField(
            value = searchQuery,
            shape = CircleShape,
            onValueChange = { query ->
                searchQuery = query

                // Cancel the previous job if the user keeps typing
                debounceJob?.cancel()

                // Start a new job with a debounce
                debounceJob = moviesSearchViewModel.viewModelScope.launch {
                    delay(500L) // 500ms debounce delay
                    moviesSearchViewModel.searchMovies(query)
                }
            },
            label = { Text("Search movies") },
            placeholder = { Text("Enter movie name...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            searchResults.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No results found",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    )
                }
            }

            else -> {
                MoviesList(navController, searchResults)
            }
        }
    }
}
