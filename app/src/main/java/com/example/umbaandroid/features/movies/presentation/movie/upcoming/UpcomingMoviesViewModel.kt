package com.example.umbaandroid.features.movies.presentation.movie.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umbaandroid.core.util.Resource
import com.example.umbaandroid.features.movies.domain.repository.MovieRepository
import com.example.umbaandroid.features.movies.presentation.state.MovieInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    @Named(value = "apiKey") private val apiKey: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieInfoState())
    val uiState: StateFlow<MovieInfoState> get() = _uiState

    init {
        fetchUpcomingMovies()
    }

    fun fetchUpcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchUpcomingMovies(apiKey = apiKey)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.loadingState()
                        }

                        is Resource.Success -> {
                            _uiState.value = _uiState.value.successState(
                                movies = resource.data,
                            )
                        }

                        is Resource.Error -> {
                            _uiState.value = _uiState.value.errorState(
                                movies = resource.data,
                                error = resource.message
                            )
                        }
                    }

                }.launchIn(this)
        }
    }
}