package com.example.moviescout.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.moviescout.data.models.Movie
import com.example.moviescout.data.repository.MoviesCategory
import com.example.moviescout.ui.components.ChipOption
import com.example.moviescout.ui.components.MovieCard
import com.example.moviescout.ui.components.MoviesListFilterChips
import com.example.moviescout.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(navController: NavHostController, innerPadding: PaddingValues) {
    val moviesViewModel: MoviesViewModel = viewModel();
    val movies by moviesViewModel.movies.collectAsState()
    val isLoading = moviesViewModel.isLoading
    var selectedCategory by remember { mutableStateOf(ChipOption(label = "Now Playing", value = MoviesCategory.NOW_PLAYING)) }
    val categories = listOf(
        ChipOption(label = "Now Playing", value = MoviesCategory.NOW_PLAYING),
        ChipOption(label = "Popular", value = MoviesCategory.POPULAR),
        ChipOption(label = "Top Rated", value = MoviesCategory.TOP_RATED),
        ChipOption(label = "Upcoming", value = MoviesCategory.UPCOMING),
    )

    LaunchedEffect(selectedCategory) {
        moviesViewModel.fetchMoviesByCategory(selectedCategory.value)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Row(Modifier.padding(top = 16.dp)
            .horizontalScroll(rememberScrollState())
        ) {
            MoviesListFilterChips(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

        }
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            MoviesList(navController, movies)
        }
    }
}

@Composable
fun MoviesList(navController: NavHostController, movies: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(top = 8.dp),
    ) {
        items(movies) { movie ->
            MovieCard(
                movie = movie,
                onClick = { navController.navigate("details/${movie.id}") }
            )
        }
    }
}
