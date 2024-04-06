package com.example.news.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAll(): Flow<List<ArticleDao>>
    @Insert
    suspend fun insert(articles: List<ArticleDao>)

    @Delete
    suspend fun remove(articles: List<ArticleDao>)

    @Query("DELETE FROM  articles")
    suspend fun clean()
}