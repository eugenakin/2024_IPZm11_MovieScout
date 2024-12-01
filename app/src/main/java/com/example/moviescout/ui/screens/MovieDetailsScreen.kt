package com.example.moviescout.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviescout.data.api.IMAGE_PATH
import com.example.moviescout.ui.components.NetworkImage
import com.example.moviescout.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(innerPadding: PaddingValues, viewModel: MovieDetailsViewModel) {
    val movieDetails by viewModel.movieDetails.collectAsState()
    val isLoading = viewModel.isLoading

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }

        if (movieDetails != null) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                NetworkImage(
                    imageUrl = "$IMAGE_PATH/${movieDetails?.poster_path!!}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                        .clip(RoundedCornerShape(12.dp)),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = movieDetails?.title!!, style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(8.dp))

                Row() {
                    movieDetails?.genres!!.forEach { genre ->
                        Text(
                            text = genre.name,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Release date: ${movieDetails?.release_date!!}",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = movieDetails?.overview!!,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}