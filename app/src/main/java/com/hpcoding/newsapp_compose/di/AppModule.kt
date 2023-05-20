package com.hpcoding.newsapp_compose.di

import com.hpcoding.newsapp_compose.data.remote.api.NewsApi
import com.hpcoding.newsapp_compose.data.repository.NewsRepositoryImpl
import com.hpcoding.newsapp_compose.domain.GetNewsUseCase
import com.hpcoding.newsapp_compose.domain.NewsRepository
import com.hpcoding.newsapp_compose.utils.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi):NewsRepository = NewsRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetNewsUseCase(repository: NewsRepository) = GetNewsUseCase(repository)

}