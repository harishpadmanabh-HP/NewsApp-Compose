package com.hpcoding.newsapp_compose.domain

import com.hpcoding.newsapp_compose.data.remote.dto.NewsListDto

interface NewsRepository {

    suspend fun getNews(search: String = ""): NewsListDto
    suspend fun getSearchQueries(search: String = ""): NewsListDto
}