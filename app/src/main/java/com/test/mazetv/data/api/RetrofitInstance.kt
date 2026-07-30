package com.test.mazetv.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class RetrofitInstance {
  val api: ApiService by lazy {
    Retrofit.Builder()
        .baseUrl("https://api.tvmaze.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
  }
}
