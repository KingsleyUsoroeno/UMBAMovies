package com.example.umbaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.umbaandroid.features.movies.presentation.Screen
import com.example.umbaandroid.features.movies.presentation.home.HomeScreen
import com.example.umbaandroid.features.movies.presentation.movie.latest.LatestMovieScreen
import com.example.umbaandroid.features.movies.presentation.movie.popular.PopularMoviesScreen
import com.example.umbaandroid.features.movies.presentation.movie.upcoming.UpcomingMoviesScreen
import com.example.umbaandroid.features.movies.presentation.movie_detail.MovieDetailScreen
import com.example.umbaandroid.ui.theme.UMBAAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UMBAAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.routeName
                    ) {
                        composable(Screen.HomeScreen.routeName) {
                            HomeScreen(navController = navController)
                        }

                        composable(route = Screen.MovieDetailScreen.routeName
                                + "/{movieType}/{movieId}",
                            arguments = listOf(
                                navArgument("movieType") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                    nullable = false
                                },
                                navArgument("movieId") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                    nullable = false
                                }
                            )) { backStackEntry ->

                            val movieType = backStackEntry.arguments?.getString("movieType") ?: ""
                            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                            MovieDetailScreen(movieType = movieType, id = movieId)
                        }

                        composable(Screen.LatestMovieScreen.routeName) {
                            LatestMovieScreen(navController)
                        }

                        composable(Screen.PopularMovieScreen.routeName) {
                            PopularMoviesScreen(navController)
                        }

                        composable(Screen.UpcomingMovieScreen.routeName) {
                            UpcomingMoviesScreen(navController)
                        }
                    }
                }
            }
        }
    }
}