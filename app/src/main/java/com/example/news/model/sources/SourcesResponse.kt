package com.example.news.model.sources


import com.squareup.moshi.Json

data class SourcesResponse(
    @Json(name = "sources")
    val sources: List<Source>,
    @Json(name = "status")
    val status: String
)