package com.hpcoding.newsapp_compose.domain

import com.hpcoding.newsapp_compose.data.remote.dto.toNewsItem
import com.hpcoding.newsapp_compose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    fun getNewsList(search: String = ""): Flow<Resource<List<NewsItem>>> = flow {
        try {
            emit(Resource.Loading())
            val news = if (search.isEmpty()) {
                repository.getNews(search).articles.map {
                    it.toNewsItem()
                }
            } else {
                repository.getSearchQueries(search).articles.map {
                    it.toNewsItem()
                }
            }
            emit(Resource.Success(news))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }
}