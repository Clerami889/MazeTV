package com.test.mazetv.di

import com.test.mazetv.data.api.ApiService
import com.test.mazetv.data.api.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun providesRetrofitInstance(): RetrofitInstance {
    return RetrofitInstance()
  }

  @Provides
  @Singleton
  fun providesApiService(retrofitInstance: RetrofitInstance): ApiService {
    return retrofitInstance.api
  }
}
