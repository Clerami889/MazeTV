package com.test.mazetv.repository

import com.test.mazetv.data.api.ApiService
import com.test.mazetv.data.models.Shows
import javax.inject.Inject

class ShowRepository @Inject constructor(private val apiService: ApiService) {

  suspend fun getShows(): List<Shows> {
    return apiService.getShows()
  }

  suspend fun getShowsById(id: Int): Shows {
    return apiService.getShowsById(id)
  }
}
