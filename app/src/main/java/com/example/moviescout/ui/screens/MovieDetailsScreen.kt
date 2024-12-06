package com.example.moviescout.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviescout.data.api.IMAGE_PATH
import com.example.moviescout.ui.components.NetworkImage
import com.example.moviescout.utils.formatReleaseDate
import com.example.moviescout.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(movieId: String, innerPadding: PaddingValues) {
    val movieDetailsViewModel: MovieDetailsViewModel = viewModel();
    val movieDetails by movieDetailsViewModel.movieDetails.collectAsState()
    val isMovieDetailsLoading = movieDetailsViewModel.isLoading

    if (isMovieDetailsLoading) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Box() {
                    NetworkImage(
                        imageUrl = "$IMAGE_PATH/${movieDetails?.poster_path!!}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(550.dp)
                            .clip(RoundedCornerShape(12.dp)),
                    )
                    Text(
                        text = String.format("%.1f", movieDetails?.vote_average!!),
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = -16.dp, y = 16.dp)
                            .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                            .border(1.dp, MaterialTheme.colorScheme.surface, CircleShape)
                            .size(80.dp)
                            .padding(vertical = 20.dp),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = movieDetails?.title!!,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row() {
                    movieDetails?.genres!!.forEach { genre ->
                        Text(
                            text = genre.name,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Release date: ${formatReleaseDate(movieDetails?.release_date!!)}",
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