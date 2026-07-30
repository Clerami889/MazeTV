package com.test.mazetv.data.api

import com.test.mazetv.data.models.Cast
import com.test.mazetv.data.models.Episode
import com.test.mazetv.data.models.Season
import com.test.mazetv.data.models.Shows
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("shows") suspend fun getShows(): List<Shows>

  @GET("shows/{id}") suspend fun getShowsById(@Path("id") id: Int): Shows

  @GET("shows/{id}/seasons") suspend fun getSeasons(@Path(value = "id") id: Int): List<Season>

  @GET("seasons/{id}/episodes")
  suspend fun getEpisodesBySeason(@Path(value = "id") id: Int): List<Episode>

  @GET("shows/{id}/cast") suspend fun getCast(@Path(value = "id") id: Int): List<Cast>
}
