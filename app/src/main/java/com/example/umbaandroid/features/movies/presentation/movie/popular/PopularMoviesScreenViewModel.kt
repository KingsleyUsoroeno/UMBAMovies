package com.example.umbaandroid.features.movies.presentation.movie.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umbaandroid.features.movies.domain.repository.MovieRepository
import com.example.umbaandroid.features.movies.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState get() = _uiState.asStateFlow()

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            movieRepository.fetchPopularMovies(apiKey = "98d4ab8983c3a5727df9ab4f565f5f4a")
                .onEach { _uiState.value = UiState.Loaded(it) }
                .catch {
                    val errorMessage = it.message ?: "Something went wrong, please try again later"
                    _uiState.value = UiState.Error(errorMessage = errorMessage)
                }.launchIn(this)
        }
    }
}