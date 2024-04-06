package com.example.news_data

import com.example.news.database.NewsDatabase
import com.example.news_data.model.Article
import com.example.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRepository(
    private val database: NewsDatabase,
    private val api: NewsApi,
){

    suspend fun getAll(apiKey: String): Flow<List<Article>> {
        return database.articlesDao
            .getAll()
            .map { articles -> articles.map { it.toArticle() } }
    }


    suspend fun search(query: String): Flow<Article> {
        api.everything()
        TODO()
    }
}