package com.example.umbaandroid.features.movies.presentation.state

import com.example.umbaandroid.features.movies.domain.model.Movie

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Loaded(val movies: List<Movie>) : UiState()
    data class Error(val errorMessage: String) : UiState()
}
