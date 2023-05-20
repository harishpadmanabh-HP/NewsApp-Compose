package com.hpcoding.newsapp_compose.data.repository

import com.hpcoding.newsapp_compose.data.remote.api.NewsApi
import com.hpcoding.newsapp_compose.data.remote.dto.NewsListDto
import com.hpcoding.newsapp_compose.domain.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: NewsApi) :NewsRepository{
    override suspend fun getNews(search:String): NewsListDto  = api.getAllNews(search)
}