package com.example.umbaandroid.remote.mapper

import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import com.example.umbaandroid.features.movies.data.remote.mapper.PopularMovieDtoMapper
import com.example.umbaandroid.features.movies.data.remote.model.MovieDto
import com.example.umbaandroid.remote.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class PopularMovieDtoMapperTest {

    private val popularMovieDtoMapper = PopularMovieDtoMapper()

    @Test
    fun `check that mapFromModel returns correct data`() {
        val movieDto: MovieDto = DummyData.movieDto
        val movieEntity: PopularMovieEntity = popularMovieDtoMapper.mapFromModel(model = movieDto)
        Truth.assertThat(movieDto.posterPath).isEqualTo(movieEntity.posterPath)
        Truth.assertThat(movieDto.title).isEqualTo(movieEntity.title)
        Truth.assertThat(movieDto.overview).isEqualTo(movieEntity.overview)
        Truth.assertThat(movieDto.adult).isEqualTo(movieEntity.adult)
    }
}