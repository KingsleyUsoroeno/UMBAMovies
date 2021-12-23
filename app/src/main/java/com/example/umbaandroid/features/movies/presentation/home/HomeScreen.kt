package com.example.umbaandroid.features.movies.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.umbaandroid.features.movies.presentation.Screen
import com.example.umbaandroid.features.movies.presentation.components.MovieOption

@Composable
fun HomeScreen(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Text(
                text = "Select a movie genre", style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                MovieOption(title = "Latest Movies") {
                    navigateToMovieList(navController = navController, arg = "Latest")
                }
                MovieOption(title = "Upcoming Movies") {
                    navigateToMovieList(navController = navController, arg = "Upcoming")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            MovieOption(title = "Popular Movies") {
                navigateToMovieList(navController = navController, arg = "Popular")
            }
        }
    }
}

private fun navigateToMovieList(navController: NavController, arg: String) {
    navController.navigate(Screen.MovieListScreen.withArgs(arg))
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}