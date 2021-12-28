package com.example.umbaandroid.features.movies.presentation.movie_detail

import androidx.lifecycle.ViewModel
import com.example.umbaandroid.features.movies.domain.model.Movie
import com.example.umbaandroid.features.movies.domain.repository.MovieRepository
import com.example.umbaandroid.features.movies.presentation.MovieType
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
                MovieType.PopularMovies.name -> {
                    emit(movieRepository.findPopularMovieById(id))
                }
                MovieType.UpcomingMovies.name -> {
                    emit(movieRepository.findUpcomingMovieById(id))
                }
                MovieType.LatestMovies.name -> {
                    emit(movieRepository.findLatestMovieById(id))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}