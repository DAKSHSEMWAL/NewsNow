package com.dakshsemwal.newsnow.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsListDTO(
    @SerializedName("articles")
    val articles: List<ArticleDTO>,
    val status: String,
    val totalResults: Int
)