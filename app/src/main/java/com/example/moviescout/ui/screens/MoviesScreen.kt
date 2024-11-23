package com.example.moviescout.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moviescout.ui.components.MovieCard
import com.example.moviescout.ui.components.MoviesListFilterChips


@Composable
fun MoviesScreen(navController: NavHostController, innerPadding: PaddingValues) {
    var selectedCategory by remember { mutableStateOf("Now Playing") }
    val categories = listOf("Now Playing", "Popular", "Top Rated", "Upcoming")


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
                MoviesList(navController)
        }
}

@Composable
fun MoviesList(navController: NavHostController) {
    val dummyMovies = List(10) { "Movie $it" }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(dummyMovies) { movie ->
            MovieCard(
                movieTitle = movie,
                onClick = { navController.navigate("details/${movie.hashCode()}") }
            )
        }
    }
}
