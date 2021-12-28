package com.example.umbaandroid.remote.mapper

import com.example.umbaandroid.features.movies.data.local.entity.LatestMovieEntity
import com.example.umbaandroid.features.movies.data.remote.mapper.LatestMovieDtoMapper
import com.example.umbaandroid.features.movies.data.remote.model.MovieDto
import com.example.umbaandroid.remote.DummyData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LatestMovieDtoMapperTest {

    private val latestMovieDtoMapper = LatestMovieDtoMapper()

    @Test
    fun `check that mapFromModel returns correct data`() {
        val movieDto: MovieDto = DummyData.movieDto
        val movieEntity: LatestMovieEntity = latestMovieDtoMapper.mapFromModel(model = movieDto)
        assertThat(movieDto.posterPath).isEqualTo(movieEntity.posterPath)
        assertThat(movieDto.title).isEqualTo(movieEntity.title)
        assertThat(movieDto.overview).isEqualTo(movieEntity.overview)
        assertThat(movieDto.adult).isEqualTo(movieEntity.adult)
    }
}