package com.example.newsapi

import com.example.newsapi.models.ArticleDTO

import com.example.newsapi.models.LanguageDTO
import com.example.newsapi.models.ResponseDTO
import com.example.newsapi.models.SortBy
import com.example.newsapi.utils.NewsApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.Date

interface NewsApi {
   @GET("/everything")

   suspend fun everything(
       @Header("X-Api-Key") apiKey: String,
       @Query("q") query: String? = null,
       @Query("from") from: Date? = null,
       @Query("to") to: Date? = null,
       @Query("languages") languages: List<LanguageDTO>? = null,
       @Query("sortBy") sortBy: SortBy? = null,
       @Query("pageSize") @androidx.annotation.IntRange(from = 0, to = 100) pageSize: Int = 100,
       @Query("page") @androidx.annotation.IntRange(from = 1) page: Int = 1,
       ): Result<ResponseDTO<ArticleDTO>>
}

fun NewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json,
): NewsApi{
    return retrofit(baseUrl,apiKey, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json,
): Retrofit {
//    val contentType = "application/json".toMediaType()

    val modifiedOkHttpClient: OkHttpClient =  (okHttpClient?.newBuilder() ?: OkHttpClient.Builder()).
    addInterceptor(NewsApiKeyInterceptor(apiKey)).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/")
        .client(modifiedOkHttpClient)
//        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
    return retrofit
}

