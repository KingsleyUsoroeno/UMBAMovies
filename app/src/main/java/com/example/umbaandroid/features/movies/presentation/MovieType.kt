package com.example.umbaandroid.features.movies.presentation

sealed class MovieType(val name: String) {
    object PopularMovies : MovieType(name = "PopularMovies")
    object UpcomingMovies : MovieType(name = "UpcomingMovies")
    object LatestMovies : MovieType(name = "LatestMovies")
}