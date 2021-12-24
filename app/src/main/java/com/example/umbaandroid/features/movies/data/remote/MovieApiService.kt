package com.example.umbaandroid.features.movies.data.remote

import com.example.umbaandroid.features.movies.data.remote.model.MovieDto
import com.example.umbaandroid.features.movies.data.remote.model.response.MovieDtoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): MovieDtoResponse

    @GET("3/movie/latest")
    suspend fun getLatestMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): MovieDto

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): MovieDtoResponse
}