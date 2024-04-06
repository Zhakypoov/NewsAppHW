package com.example.news_main

import com.example.news_data.ArticlesRepository
import com.example.news_data.model.Article
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(private val repository: ArticlesRepository) {
    operator suspend fun invoke(): Flow<Article> {
        return repository.request()
    }
}