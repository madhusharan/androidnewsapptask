package com.newsfeed.app.model

import com.newsfeed.app.roomdb.NewsArticle

data class NewsResponse(
    val status: String,
    val articles: List<NewsArticle>
)