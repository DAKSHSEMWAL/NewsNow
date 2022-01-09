package com.dakshsemwal.newsnow.data.remote.dto

import com.dakshsemwal.newsnow.domain.model.Article
import com.google.gson.annotations.SerializedName

data class ArticleDTO(
    val author: String? = null,
    val content: String?=null,
    val description: String? = null,
    val publishedAt: String? = null,
    @SerializedName("source")
    val source: SourceDTO? = null,
    val title: String? = null,
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null
)

fun ArticleDTO.toArticle(): Article =
    Article(description = description, title = title, urlToImage = urlToImage)