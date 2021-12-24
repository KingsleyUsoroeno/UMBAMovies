package com.example.umbaandroid.features.movies.data.remote.mapper

import com.example.umbaandroid.features.movies.data.local.entity.UpcomingMovieEntity
import com.example.umbaandroid.features.movies.data.remote.mapper.base.RemoteModelMapper
import com.example.umbaandroid.features.movies.data.remote.model.MovieDto

class UpcomingMovieDtoMapper : RemoteModelMapper<MovieDto, UpcomingMovieEntity> {
    override fun mapFromModel(model: MovieDto): UpcomingMovieEntity {
        return UpcomingMovieEntity(
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