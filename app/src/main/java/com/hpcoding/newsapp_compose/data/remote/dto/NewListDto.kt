package com.hpcoding.newsapp_compose.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.hpcoding.newsapp_compose.domain.NewsItem

@Keep
data class NewsListDto(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)


@Keep
data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)


@Keep
data class Article(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?
)

fun Article.toNewsItem() = NewsItem(
    imageUrl = urlToImage ?: "",
    title = title ?: "",
    description = description ?: "",
    publishedAt = publishedAt ?: "",
    author = author ?: "",
    content = content ?: ""
)