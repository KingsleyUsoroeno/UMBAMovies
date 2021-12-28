package com.example.umbaandroid.remote.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class RequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            POPULAR_MOVIES_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("response/popular_movies.json"))
            }
            UPCOMING_MOVIES_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson("response/upcoming_movies.json")
                    )
            }
            LATEST_MOVIES_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("response/latest_movies.json"))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
        }
    }
}
