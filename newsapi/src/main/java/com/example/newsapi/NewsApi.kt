package com.example.newsapi

import com.example.newsapi.models.Article
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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
): NewsApi{
    val retrofit = retrofit(baseUrl, okHttpClient)
    return retrofit.create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .run { if(okHttpClient != null) client(okHttpClient) else this }
        .build()
    return retrofit
}

