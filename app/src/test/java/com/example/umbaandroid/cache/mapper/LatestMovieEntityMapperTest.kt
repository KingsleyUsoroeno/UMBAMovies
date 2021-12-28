package com.example.umbaandroid.cache.mapper

import com.example.umbaandroid.cache.entity.DummyData
import com.example.umbaandroid.features.movies.data.local.entity.LatestMovieEntity
import com.example.umbaandroid.features.movies.data.local.mapper.latest_movies.LatestMovieEntityMapper
import com.example.umbaandroid.features.movies.domain.model.Movie
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LatestMovieEntityMapperTest {

    private val latestMovieEntityMapper = LatestMovieEntityMapper()

    @Test
    fun `check that mapToModel returns correct data`() {
        val entity: LatestMovieEntity = DummyData.latestMovieEntity
        val movie: Movie = latestMovieEntityMapper.mapToModel(entity)
        assertThat(movie.title).isEqualTo(entity.title)
        assertThat(movie.overview).isEqualTo(movie.overview)
        assertThat(movie.releaseDate).isEqualTo(entity.releaseDate)
        assertThat(movie.adult).isEqualTo(entity.adult)
    }

    @Test
    fun `check that mapToEntity returns correct data`() {
        val model: Movie = DummyData.movie
        val entity: LatestMovieEntity = latestMovieEntityMapper.mapToEntity(model)
        assertThat(model.title).isEqualTo(entity.title)
        assertThat(model.overview).isEqualTo(entity.overview)
        assertThat(model.id).isEqualTo(entity.id)
        assertThat(model.releaseDate).isEqualTo(entity.releaseDate)
    }
}