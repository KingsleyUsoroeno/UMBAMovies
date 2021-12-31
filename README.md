# UMBAMovies
A movies application built with jetpack compose that supports offline caching using the room persistence library and leverages the public Movies Database API


# UMBAMovies
### Getting Started 

UMBA Movies is my first take at building a movies application that leverages upon jetpack compose for building declarative UIs in android
whilst providing offline caching using the room database and retrofit for fecthing our remote movie data from the [Movies Database API](https://developers.themoviedb.org/)

The App Makes use of some of the most popular Jetpack libraries which has been listed below

## Libraries Used

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way.
* [Navigation Component](https://developer.android.com/guide/navigation) to handle all navigations and also passing of data between composables.
* [Material Design](https://material.io/develop/android/docs/getting-started/)
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) used to manage the local storage i.e. `writing to and reading from the database`. Coroutines helps in managing background threads and reduces the need for callbacks.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [Android KTX](https://developer.android.com/kotlin/ktx) which helps to write more concise, idiomatic Kotlin code.
* [Retrofit](https://square.github.io/retrofit/) for interacting with our remote data source.

## Architecture
The architecture of this application relies and complies with the following points below:
* Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)(MVVM) which facilitates a separation of development of the graphical user interface from its varying datasources.
* [Android architecture components](https://developer.android.com/topic/libraries/architecture/) which help's to keep the application robust, testable, and maintainable

### Screenshots

<p float="left">
  <img src="https://github.com/KingsleyUsoroeno/UMBAMovies/blob/master/app/screenshots/home_screen_screenshot.png" width="300" />
  <img src="https://github.com/KingsleyUsoroeno/UMBAMovies/blob/master/app/screenshots/movie_detail_screenshot.png" width="300" /> 
  <img src="https://github.com/KingsleyUsoroeno/UMBAMovies/blob/master/app/screenshots/popular_movie_screen_screen_shot.png"   width="300" />
</p>

<p float="left">
  <img src="https://github.com/KingsleyUsoroeno/UMBAMovies/blob/master/app/screenshots/upcoming_movies_screenshot.png" width="300"  />
</p>
