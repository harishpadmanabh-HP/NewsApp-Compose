package com.hpcoding.newsapp_compose.di

import com.hpcoding.newsapp_compose.BuildConfig
import com.hpcoding.newsapp_compose.data.remote.api.NewsApi
import com.hpcoding.newsapp_compose.data.repository.NewsRepositoryImpl
import com.hpcoding.newsapp_compose.domain.GetNewsUseCase
import com.hpcoding.newsapp_compose.domain.NewsRepository
import com.hpcoding.newsapp_compose.utils.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(httpLoggingInterceptor)
                }
            }.readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .build()


    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient): NewsApi = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi):NewsRepository = NewsRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetNewsUseCase(repository: NewsRepository) = GetNewsUseCase(repository)

}