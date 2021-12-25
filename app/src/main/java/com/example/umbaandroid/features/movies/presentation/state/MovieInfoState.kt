package com.example.umbaandroid.features.movies.presentation.state

import com.example.umbaandroid.features.movies.domain.model.Movie

data class MovieInfoState(
    val movies: List<Movie>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    fun loadingState(): MovieInfoState = copy(isLoading = true)

    fun errorState(error: String?, movies: List<Movie>?): MovieInfoState =
        copy(
            errorMessage = error,
            isLoading = false,
            movies = movies,
        )

    fun successState(movies: List<Movie>?): MovieInfoState =
        copy(
            isLoading = false,
            errorMessage = null,
            movies = movies
        )
}