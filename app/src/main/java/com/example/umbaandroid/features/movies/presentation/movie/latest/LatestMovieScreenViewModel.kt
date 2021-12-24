package com.example.umbaandroid.features.movies.presentation.movie.latest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umbaandroid.features.movies.domain.repository.MovieRepository
import com.example.umbaandroid.features.movies.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LatestMovieScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    @Named(value = "apiKey") private val apiKey: String
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState get() = _uiState.asStateFlow()

    init {
        fetchLatestMovies()
    }

    private fun fetchLatestMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            movieRepository.fetchLatestMovies(apiKey = apiKey)
                .onEach { _uiState.value = UiState.Loaded(it) }
                .catch {
                    val errorMessage = it.message ?: "Something went wrong, please try again later"
                    _uiState.value = UiState.Error(errorMessage = errorMessage)
                }.launchIn(this)
        }
    }
}