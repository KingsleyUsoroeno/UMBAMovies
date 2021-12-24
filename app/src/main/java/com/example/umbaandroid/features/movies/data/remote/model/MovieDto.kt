package com.example.umbaandroid.features.movies.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)
