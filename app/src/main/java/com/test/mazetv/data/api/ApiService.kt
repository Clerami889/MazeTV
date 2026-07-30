package com.test.mazetv.data.api

import com.test.mazetv.data.models.Shows
import retrofit2.http.GET

interface ApiService {

  @GET("shows") suspend fun getShows(): List<Shows>
}
