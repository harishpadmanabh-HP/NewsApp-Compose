package com.hpcoding.newsapp_compose.data.remote.api

import com.hpcoding.newsapp_compose.data.remote.dto.NewsListDto
import com.hpcoding.newsapp_compose.utils.ApiKey
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?from=2023-04-20&sortBy=publishedAt&apiKey=$ApiKey&country=us")
    suspend fun getAllNews(
        @Query("q") search: String
    ): NewsListDto


}