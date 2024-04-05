package com.example.newsapi

import com.example.newsapi.models.Article

import com.example.newsapi.models.Language
import com.example.newsapi.models.Response
import com.example.newsapi.models.SortBy
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface NewsApi {
   @GET("/everything")
   suspend fun everything(
       @Query("q") query: String? = null,
       @Query("from") from: Date? = null,
       @Query("to") to: Date? = null,
       @Query("languages") languages: List<Language>? = null,
       @Query("sortBy") sortBy: SortBy? = null,
       @Query("pageSize") @androidx.annotation.IntRange(from = 0, to = 100) pageSize: Int = 100,
       @Query("page") @androidx.annotation.IntRange(from = 1) page: Int = 1,
       ): Response<Article>
}

fun NewsApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json,
): NewsApi{
    return retrofit(baseUrl, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json,
): Retrofit {
//    val contentType = "application/json".toMediaType()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/")
//        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
    return retrofit
}

