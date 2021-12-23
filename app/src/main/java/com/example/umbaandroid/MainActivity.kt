package com.example.umbaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.umbaandroid.features.movies.presentation.Screen
import com.example.umbaandroid.features.movies.presentation.home.HomeScreen
import com.example.umbaandroid.features.movies.presentation.movie_list.MovieListScreen
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
                        composable(
                            Screen.MovieListScreen.routeName + "/{movieType}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                    nullable = false
                                }
                            )) {

                            val movieType = it.arguments?.getString("movieType") ?: ""
                            MovieListScreen(movieType = movieType, navController = navController)
                        }
                    }
                }
            }
        }
    }
}