package com.dakshsemwal.newsnow.presentation.ui

import com.dakshsemwal.newsnow.data.remote.dto.NewsListDTO
import com.dakshsemwal.newsnow.data.remote.dto.toArticle
import com.dakshsemwal.newsnow.domain.model.Article


data class NewsState(
    val isLoading: Boolean = false,
    val newsListDTO: NewsListDTO? = null,
    val error: String = ""
) {
    val newsList: List<Article>? = newsListDTO?.articles?.map { it.toArticle() }
}
