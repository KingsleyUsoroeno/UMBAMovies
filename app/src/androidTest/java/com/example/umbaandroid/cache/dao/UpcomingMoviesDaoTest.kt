package com.example.umbaandroid.cache.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.umbaandroid.cache.DummyData
import com.example.umbaandroid.cache.util.Utils
import com.example.umbaandroid.features.movies.data.local.MovieDatabase
import com.example.umbaandroid.features.movies.data.local.dao.UpcomingMoviesDao
import com.example.umbaandroid.features.movies.data.local.entity.UpcomingMovieEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UpcomingMoviesDaoTest {

    private lateinit var movieDatabase: MovieDatabase
    private lateinit var upcomingMoviesDao: UpcomingMoviesDao


    @Before
    fun initDb() {
        movieDatabase = Utils.makeMovieDatabase(
            InstrumentationRegistry.getInstrumentation().targetContext,
        )
        upcomingMoviesDao = movieDatabase.upcomingMoviesDao
    }

    @Test
    fun insertSavesDataToDatabase(): Unit = runBlocking {
        val upcomingMovieEntity = DummyData.upcomingMovieEntity
        upcomingMoviesDao.insert(entity = upcomingMovieEntity)
        val cachedMovies = upcomingMoviesDao.getUpcomingMovies()
        assertThat(cachedMovies.size).isEqualTo(1)
        assertThat(cachedMovies.first().title).isEqualTo("Venom: Let There Be Carnage")
        assertThat(cachedMovies.isNotEmpty())
    }

    @Test
    fun getUpcomingMoviesReturnsAllCachedMovieData(): Unit = runBlocking {
        val cachedMovieEntities: List<UpcomingMovieEntity> =
            List(size = 4) { DummyData.upcomingMovieEntity }

        upcomingMoviesDao.insert(entities = cachedMovieEntities)

        val retrievedMovies = upcomingMoviesDao.getUpcomingMovies()
        assertThat(
            retrievedMovies == cachedMovieEntities.sortedWith(
                compareBy(
                    { it.id },
                    { it.id })
            )
        )
    }

    @Test
    fun clearTableClearsData(): Unit = runBlocking {
        val upcomingMovieEntity = DummyData.upcomingMovieEntity
        upcomingMoviesDao.insert(entity = upcomingMovieEntity)

        upcomingMoviesDao.clearTable()
        assertThat(upcomingMoviesDao.getUpcomingMovies().isEmpty())
    }

    @Test
    fun findMovieByIdReturnsCorrectDataWithValidId(): Unit = runBlocking {
        val movieEntity: UpcomingMovieEntity = DummyData.upcomingMovieEntity
        upcomingMoviesDao.insert(entity = movieEntity)

        val upcomingMovieEntity = upcomingMoviesDao.findMovieById(movieEntity.id)

        assertThat(upcomingMovieEntity).isNotEqualTo(null)
        assertThat(upcomingMovieEntity?.title).isEqualTo("Venom: Let There Be Carnage")
    }

    @Test
    fun insertAndDeleteInTransactionClearsAndCachesNewData(): Unit = runBlocking {
        val movieEntity: UpcomingMovieEntity = DummyData.upcomingMovieEntity
        upcomingMoviesDao.insert(entity = movieEntity)
        upcomingMoviesDao.clearTable()
        assertThat(upcomingMoviesDao.getUpcomingMovies()).isEmpty()
        upcomingMoviesDao.insert(entity = movieEntity)
        assertThat(upcomingMoviesDao.getUpcomingMovies()).isNotEmpty()
    }


    @After
    fun closeDb() {
        movieDatabase.close()
    }
}