package com.example.moviescout

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviescout.ui.components.BottomBar
import com.example.moviescout.ui.components.TopBar
import com.example.moviescout.ui.screens.MovieDetailsScreen
import com.example.moviescout.ui.screens.MoviesScreen
import com.example.moviescout.ui.screens.MoviesSearchScreen
import com.example.moviescout.ui.screens.MoviesWatchLaterScreen
import com.example.moviescout.ui.theme.MoviescoutTheme
import com.example.moviescout.viewmodel.MovieDetailsViewModel
import com.example.moviescout.viewmodel.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviescoutTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(navController)
                    },
                    bottomBar = {
                        BottomBar(navController)
                    }
                ) { innerPadding ->
                    AppNavHost(navController, innerPadding)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            MoviesScreen(navController, innerPadding, MoviesViewModel())
        }
        composable(route = "details/{movieId}") { backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getString("movieId"))
            MovieDetailsScreen(innerPadding, MovieDetailsViewModel(movieId))
        }
        composable(route = "watch-later") {
            MoviesWatchLaterScreen(navController, innerPadding);
        }
        composable(route = "search") {
            MoviesSearchScreen(navController, innerPadding)
        }
    }
}
