package com.example.umbaandroid.features.movies.presentation

sealed class Screen(val routeName: String) {
    object HomeScreen : Screen("home_screen")
    object MovieListScreen : Screen("movie_list_screen")
    object MovieDetailScreen : Screen("movie_detail_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(routeName)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}