package com.test.mazetv.data.api

import com.test.mazetv.data.models.Shows
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("shows") suspend fun getShows(): List<Shows>

  @GET("shows/{id}") suspend fun getShowsById(@Path("id") id: Int): Shows
}
