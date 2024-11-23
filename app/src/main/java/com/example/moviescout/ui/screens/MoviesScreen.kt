package com.example.moviescout.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moviescout.ui.components.MovieCard

@Composable
fun MoviesScreen(navController: NavHostController, innerPadding: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

