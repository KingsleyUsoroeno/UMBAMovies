package com.example.umbaandroid.remote.service

import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import com.example.umbaandroid.features.movies.data.local.entity.UpcomingMovieEntity
import com.example.umbaandroid.features.movies.data.remote.MovieApiService
import com.example.umbaandroid.features.movies.data.remote.mapper.PopularMovieDtoMapper
import com.example.umbaandroid.features.movies.data.remote.mapper.UpcomingMovieDtoMapper
import com.example.umbaandroid.features.movies.data.remote.model.MovieDto
import com.example.umbaandroid.features.movies.data.remote.model.response.MovieDtoResponse
import com.example.umbaandroid.remote.utils.RequestDispatcher
import com.example.umbaandroid.remote.utils.makeTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieApiService: MovieApiService
    private val popularMovieDtoMapper = PopularMovieDtoMapper()
    private val upcomingMovieDtoMapper = UpcomingMovieDtoMapper()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        movieApiService = makeTestApiService(mockWebServer)
    }

    @Test
    fun `check that getPopularMovies returns correct data`(): Unit = runBlocking {
        val response: MovieDtoResponse = movieApiService.getPopularMovies("API_KEY")

        val popularMovieDto: MovieDto = response.movies.first()
        val popularMovieEntities: List<PopularMovieEntity> =
            popularMovieDtoMapper.mapModelList(response.movies)

        assertThat(popularMovieDto.title).isEqualTo("Spider-Man: No Way Home")
        assertThat(popularMovieDto.overview).isEqualTo("Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.")
        assertThat(popularMovieDto.adult).isEqualTo(false)
        assertThat(popularMovieDto.posterPath).isEqualTo("/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg")

        assertThat(popularMovieEntities.size).isEqualTo(response.movies.size)
    }

    @Test
    fun `check that getLatestMovie returns correct data`(): Unit = runBlocking {
        val latestMovieDto: MovieDto = movieApiService.getLatestMovie("API_KEY")
        assertThat(latestMovieDto.title).isEqualTo("Jeviště zblízka")
        assertThat(latestMovieDto.overview).isEqualTo("")
        assertThat(latestMovieDto.adult).isEqualTo(false)
        assertThat(latestMovieDto.posterPath).isEqualTo(null)
    }

    @Test
    fun `check that getUpcomingMovies returns correct data`(): Unit = runBlocking {
        val response: MovieDtoResponse = movieApiService.getUpcomingMovies("API_KEY")

        val upcomingMovieEntities: List<UpcomingMovieEntity> =
            upcomingMovieDtoMapper.mapModelList(response.movies)

        val upcomingMovieDto: MovieDto = response.movies.first()
        assertThat(upcomingMovieDto.title).isEqualTo("Spider-Man: No Way Home")
        assertThat(upcomingMovieDto.releaseDate).isEqualTo("2021-12-15")
        assertThat(upcomingMovieDto.adult).isEqualTo(false)
        assertThat(upcomingMovieDto.originalTitle).isEqualTo("Spider-Man: No Way Home")

        assertThat(upcomingMovieEntities.size).isEqualTo(response.movies.size)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}