package com.dakshsemwal.newsnow.data.remote.dto

import com.dakshsemwal.newsnow.domain.model.Article
import com.google.gson.annotations.SerializedName

data class ArticleDTO(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @SerializedName("source")
    val source: SourceDTO,
    val title: String,
    val url: String,
    val urlToImage: String
)

fun ArticleDTO.toArticle(): Article =
    Article(description = description, title = title, urlToImage = urlToImage)