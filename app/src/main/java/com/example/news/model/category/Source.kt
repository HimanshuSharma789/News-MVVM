package com.example.news.model.category

import com.squareup.moshi.Json
import java.io.Serializable

data class FromSource(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
) : Serializable