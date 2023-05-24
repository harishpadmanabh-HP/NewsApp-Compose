package com.hpcoding.newsapp_compose.data.remote.api

import com.hpcoding.newsapp_compose.data.remote.dto.NewsListDto
import com.hpcoding.newsapp_compose.utils.ApiKey
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?country=us&sortBy=publishedAt&apiKey=ac500ae75cdf4f769c5a64f5ebd785a1")
    suspend fun getAllNews(
        @Query("q") search: String
    ): NewsListDto

    @GET("everything?apiKey=ac500ae75cdf4f769c5a64f5ebd785a1")
    suspend fun getSearchResults(@Query("q") search: String): NewsListDto


}