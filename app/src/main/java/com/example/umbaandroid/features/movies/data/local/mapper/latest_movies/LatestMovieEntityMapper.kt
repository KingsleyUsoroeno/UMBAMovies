package com.example.umbaandroid.features.movies.data.local.mapper.latest_movies

import com.example.umbaandroid.features.movies.data.local.entity.LatestMovieEntity
import com.example.umbaandroid.features.movies.data.local.mapper.base.CacheEntityMapper
import com.example.umbaandroid.features.movies.domain.model.Movie
import javax.inject.Inject

class LatestMovieEntityMapper @Inject constructor() :
    CacheEntityMapper<Movie, LatestMovieEntity> {

    override fun mapToModel(entity: LatestMovieEntity): Movie {
        return Movie(
            id = entity.id,
            adult = entity.adult,
            backdropPath = entity.backdropPath ?: "",
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            posterPath = entity.posterPath,
            title = entity.title,
            voteAverage = entity.voteAverage,
            releaseDate = entity.releaseDate
        )
    }

    override fun mapToEntity(model: Movie): LatestMovieEntity {
        return LatestMovieEntity(
            id = model.id,
            adult = model.adult,
            backdropPath = model.backdropPath,
            originalTitle = model.originalTitle,
            overview = model.overview,
            posterPath = model.posterPath,
            title = model.title,
            voteAverage = model.voteAverage,
            releaseDate = model.releaseDate
        )
    }
}