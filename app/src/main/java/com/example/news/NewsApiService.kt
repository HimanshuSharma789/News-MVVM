package com.example.news

import com.example.news.model.category.NewsResponse
import com.example.news.model.sources.SourcesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://newsapi.org/v2/"
//const val API_KEY = "<your-api-key-here>"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("sources")
    suspend fun getSources(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): SourcesResponse

    @GET("top-headlines")
    suspend fun getSpecificSources(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}

object NewsApi {
    val networkService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}


