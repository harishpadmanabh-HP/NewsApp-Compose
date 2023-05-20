package com.hpcoding.newsapp_compose.domain

import androidx.annotation.Keep

@Keep
data class NewsItem(
    val imageUrl:String,
    val title:String,
    val description:String,
    val publishedAt:String?,
    val content:String?,
    val author:String?

)