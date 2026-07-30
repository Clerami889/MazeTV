package com.test.mazetv.repository

import com.test.mazetv.data.api.ApiService
import com.test.mazetv.data.models.Cast
import com.test.mazetv.data.models.Episode
import com.test.mazetv.data.models.Season
import com.test.mazetv.data.models.Shows
import javax.inject.Inject

class ShowRepository @Inject constructor(private val apiService: ApiService) {

  suspend fun getShows(): List<Shows> {
    return apiService.getShows()
  }

  suspend fun getShowsById(id: Int): Shows {
    return apiService.getShowsById(id)
  }

  suspend fun getSeasons(id: Int): List<Season> {
    return apiService.getSeasons(id)
  }

  suspend fun getCast(id: Int): List<Cast> {
    return apiService.getCast(id)
  }

  suspend fun getEpisodesBySeason(id: Int): List<Episode> {
    return apiService.getEpisodesBySeason(id)
  }
}
