package com.example.umbaandroid.features.movies.data.remote.mapper

import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import com.example.umbaandroid.features.movies.data.remote.mapper.base.RemoteModelMapper
import com.example.umbaandroid.features.movies.data.remote.model.MovieDto

class PopularMovieDtoMapper : RemoteModelMapper<MovieDto, PopularMovieEntity> {
    override fun mapFromModel(model: MovieDto): PopularMovieEntity {
        return PopularMovieEntity(
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