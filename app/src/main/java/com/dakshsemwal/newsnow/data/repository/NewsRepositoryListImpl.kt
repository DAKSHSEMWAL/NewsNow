package com.dakshsemwal.newsnow.data.repository

import com.dakshsemwal.newsnow.BuildConfig.CONSUMER_KEY
import com.dakshsemwal.newsnow.data.remote.NewsSourceAPI
import com.dakshsemwal.newsnow.data.remote.dto.NewsListDTO
import com.dakshsemwal.newsnow.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryListImpl @Inject constructor(val newsSourceAPI: NewsSourceAPI) :
    NewsRepository {
    override suspend fun getNews(country: String): NewsListDTO =
        newsSourceAPI.getTrendingNews(CONSUMER_KEY, country)
}