package com.example.umbaandroid.features.movies.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LatestMovies")
data class LatestMovieEntity(
    @NonNull @PrimaryKey val adult: Boolean,
    val backdropPath: String?,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val releaseDate: String,
)
