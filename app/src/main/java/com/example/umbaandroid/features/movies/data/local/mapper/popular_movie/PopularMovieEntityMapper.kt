package com.example.umbaandroid.features.movies.data.local.mapper.popular_movie

import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import com.example.umbaandroid.features.movies.data.local.mapper.base.CacheEntityMapper
import com.example.umbaandroid.features.movies.domain.model.Movie
import javax.inject.Inject

class PopularMovieEntityMapper @Inject constructor() :
    CacheEntityMapper<Movie, PopularMovieEntity> {

    override fun mapToModel(entity: PopularMovieEntity): Movie {
        return Movie(
            id = entity.id,
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            voteAverage = entity.voteAverage
        )
    }

    override fun mapToEntity(model: Movie): PopularMovieEntity {
        return PopularMovieEntity(
            id = model.id,
            adult = model.adult,
            backdropPath = model.backdropPath,
            originalTitle = model.originalTitle,
            overview = model.overview,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            title = model.title,
            voteAverage = model.voteAverage
        )
    }
}