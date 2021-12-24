package com.example.umbaandroid.features.movies.domain.repository

import com.example.umbaandroid.features.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun findPopularMovieById(id: Int): Movie?

    suspend fun findUpcomingMovieById(id: Int): Movie?

    suspend fun findLatestMovieById(id: Int): Movie?

    fun fetchPopularMovies(apiKey: String): Flow<List<Movie>>

    fun fetchUpcomingMovies(apiKey: String): Flow<List<Movie>>

    fun fetchLatestMovies(apiKey: String): Flow<List<Movie>>
}