package com.example.newsapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 "status": "ok",
 "totalResults": 218,
 +"articles": [ .. ]
  */

 @Serializable
class ResponseDTO<E> (
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int,
    @SerialName("articles") val articles: List<E>
 ){
}

