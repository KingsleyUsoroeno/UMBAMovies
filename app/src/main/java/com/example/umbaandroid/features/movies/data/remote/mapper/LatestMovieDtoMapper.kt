package com.example.umbaandroid.features.movies.data.remote.mapper

import com.example.umbaandroid.features.movies.data.local.entity.LatestMovieEntity
import com.example.umbaandroid.features.movies.data.remote.mapper.base.RemoteModelMapper
import com.example.umbaandroid.features.movies.data.remote.model.MovieDto
import javax.inject.Inject

class LatestMovieDtoMapper @Inject constructor() : RemoteModelMapper<MovieDto, LatestMovieEntity> {
    override fun mapFromModel(model: MovieDto): LatestMovieEntity {
        return LatestMovieEntity(
            id = model.id,
            adult = model.adult,
            backdropPath = model.backdropPath ?: "",
            originalTitle = model.originalTitle,
            overview = model.overview,
            posterPath = model.posterPath ?: "",
            releaseDate = model.releaseDate,
            title = model.title,
            voteAverage = model.voteAverage
        )
    }
}