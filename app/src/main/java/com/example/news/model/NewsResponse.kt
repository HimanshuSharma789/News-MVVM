package com.example.news.model

import com.squareup.moshi.Json
import java.io.Serializable

data class NewsResponse(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
) : Serializable