package com.example.umbaandroid.features.movies.presentation.movie_detail

import androidx.lifecycle.ViewModel
import com.example.umbaandroid.features.movies.domain.model.Movie
import com.example.umbaandroid.features.movies.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MovieDetailScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovieById(movieType: String, id: Int): Flow<Movie?> {
        return flow {
            when (movieType) {
                "Popular Movies" -> {
                    emit(movieRepository.findPopularMovieById(id))
                }
                "Upcoming Movies" -> {
                    emit(movieRepository.findUpcomingMovieById(id))
                }
                "Latest Movies" -> {
                    emit(movieRepository.findLatestMovieById(id))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}